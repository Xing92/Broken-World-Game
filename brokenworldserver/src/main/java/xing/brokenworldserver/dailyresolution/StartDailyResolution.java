package xing.brokenworldserver.dailyresolution;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;

import xing.brokenworldserver.model.Attack;
import xing.brokenworldserver.model.Kingdom;
import xing.brokenworldserver.model.Move;
import xing.brokenworldserver.utils.HibernateUtils;
import xing.brokenworldserver.utils.Log;

public class StartDailyResolution extends TimerTask {

	private static float landTakenPercentage = 0.1f;

	public void startDailyResolution() {

		resolveMoves();
		resolveAttacks();

	}

	private void resolveMoves() {
		Log.info("Resolving Moves");
		List<Move> moves = getMoves();
		for (Move move : moves) {
			Kingdom kingdom = move.getKingdom();
			Log.info("Resolving Move for Kingdom: {}", kingdom.getName());
			kingdom.changeMiners(move.getChangeMiners());
			Log.info("Changing miners by: {}. It resulted in {} miners.", move.getChangeMiners(), kingdom.getMiners());
			kingdom.changeArmy(move.getChangeArmy());
			Log.info("Changing army by: {}. It resulted in {} army.", move.getChangeArmy(), kingdom.getArmy());

			try {
				HibernateUtils.getSession().saveOrUpdate(kingdom);
				HibernateUtils.getSession().delete(move);
			} finally {
				HibernateUtils.getTransaction().commit();
				HibernateUtils.getSession().close();
			}
		}
	}

	private List<Move> getMoves() {
		List<Move> moves;
		try {
			moves = HibernateUtils.getSession().createCriteria(Move.class).list();//createQuery("from Move order by id ASC").list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return moves;
	}

	private void resolveAttacks() {
		Log.info("Resolving Attacks");

		List<Attack> attacks = getAttacks();
		for (Attack attack : attacks) {
			Log.info("Resolving Attack with ID: {}", attack.getId());
			Kingdom source = attack.getSource();
			Kingdom destination = attack.getDestination();

			boolean isAttackWon = isAttackWon(attack);
			if (isAttackWon) {
				source.incrementAttacksWon();
				destination.incrementDefendsLost();

				int landTaken = (int) (destination.getLand() * landTakenPercentage);
				source.changeLand(landTaken);
				destination.changeLand(-landTaken);

			} else {
				source.incrementAttacksLost();
				destination.incrementDefendsWon();
			}

			try {
				HibernateUtils.getSession().saveOrUpdate(source);
				HibernateUtils.getSession().saveOrUpdate(destination);
				HibernateUtils.getSession().delete(attack);
			} finally {
				HibernateUtils.getTransaction().commit();
				HibernateUtils.getSession().close();
				// HibernateUtils.getFactory().close();
			}

		}
	}

	private boolean isAttackWon(Attack attack) {

		Kingdom destination = attack.getDestination();
		int peopleAttack = attack.getPeople();
		int peopleDefend = destination.getPeople();

		return (peopleAttack >= peopleDefend);
	}

	private List<Attack> getAttacks() {
		List<Attack> attacks;
		try {
			attacks = HibernateUtils.getSession().createCriteria(Attack.class).list();//.createQuery("from Attack order by id ASC").list();
		} finally {
			HibernateUtils.getSession().close();
		}

		return attacks;

	}

	@Override
	public void run() {
		Log.info("===== Started Daily {} =====", LocalDateTime.now().toLocalDate().toString());
		startDailyResolution();
		Log.info("===== Finished Daily {} =====", LocalDateTime.now().toLocalDate().toString());
	}

}
