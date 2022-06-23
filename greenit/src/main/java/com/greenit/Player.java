package com.greenit;


import com.greenit.game.Application;
import com.greenit.game.Card;
import com.greenit.game.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Complete the hackathon before your opponent by following the principles of Green IT
 **/
public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            String gamePhase = in.next(); // can be MOVE, GIVE_CARD, THROW_CARD, PLAY_CARD or RELEASE
            System.err.println("gamePhase: " + gamePhase);
            int applicationsCount = in.nextInt();
            List<Application> applicationList = new ArrayList<>();
            for (int i = 0; i < applicationsCount; i++) {
                String objectType = in.next();
                int id = in.nextInt();
                int trainingNeeded = in.nextInt(); // number of TRAINING skills needed to release this application
                int codingNeeded = in.nextInt(); // number of CODING skills needed to release this application
                int dailyRoutineNeeded = in.nextInt(); // number of DAILY_ROUTINE skills needed to release this application
                int taskPrioritizationNeeded = in.nextInt(); // number of TASK_PRIORITIZATION skills needed to release this application
                int architectureStudyNeeded = in.nextInt(); // number of ARCHITECTURE_STUDY skills needed to release this application
                int continuousDeliveryNeeded = in.nextInt(); // number of CONTINUOUS_DELIVERY skills needed to release this application
                int codeReviewNeeded = in.nextInt(); // number of CODE_REVIEW skills needed to release this application
                int refactoringNeeded = in.nextInt(); // number of REFACTORING skills needed to release this application
                Application application = new Application(id, objectType, trainingNeeded, codingNeeded, dailyRoutineNeeded, taskPrioritizationNeeded, architectureStudyNeeded, continuousDeliveryNeeded, codeReviewNeeded, refactoringNeeded);
                applicationList.add(application);
                application.debug();
            }
            List<Team> playerGameList = new ArrayList<>();
            Team myPlayer = null;
            for (int i = 0; i < 2; i++) {
                int playerLocation = in.nextInt(); // id of the zone in which the player is located
                int playerScore = in.nextInt();
                int playerPermanentDailyRoutineCards = in.nextInt(); // number of DAILY_ROUTINE the player has played. It allows them to take cards from the adjacent zones
                int playerPermanentArchitectureStudyCards = in.nextInt(); // number of ARCHITECTURE_STUDY the player has played. It allows them to draw more cards
                Team team = new Team(playerLocation, playerScore, playerPermanentDailyRoutineCards, playerPermanentArchitectureStudyCards);
                playerGameList.add(team);
                team.debug();
                if (i == 0) {
                    myPlayer = team;
                }
            }
            int cardLocationsCount = in.nextInt();
            List<Card> cards = new ArrayList<>();
            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < cardLocationsCount; i++) {
                String cardsLocation = in.next(); // the location of the card list. It can be HAND, DRAW, DISCARD or OPPONENT_CARDS (AUTOMATED and OPPONENT_AUTOMATED will appear in later leagues)
                int trainingCardsCount = in.nextInt();
                int codingCardsCount = in.nextInt();
                int dailyRoutineCardsCount = in.nextInt();
                int taskPrioritizationCardsCount = in.nextInt();
                int architectureStudyCardsCount = in.nextInt();
                int continuousDeliveryCardsCount = in.nextInt();
                int codeReviewCardsCount = in.nextInt();
                int refactoringCardsCount = in.nextInt();
                int bonusCardsCount = in.nextInt();
                int technicalDebtCardsCount = in.nextInt();
                Card card = new Card(cardsLocation, trainingCardsCount, codingCardsCount, dailyRoutineCardsCount, taskPrioritizationCardsCount, architectureStudyCardsCount, continuousDeliveryCardsCount, codeReviewCardsCount, refactoringCardsCount, bonusCardsCount, technicalDebtCardsCount);
                cards.add(card);
                card.debug();
                if (cardsLocation.equals("HAND")) {
                    hand.add(card);
                }
            }
            int possibleMovesCount = in.nextInt();
            if (in.hasNextLine()) {
                in.nextLine();
            }
            for (int i = 0; i < possibleMovesCount; i++) {
                String possibleMove = in.nextLine();
                //le nombre de coup à jouer
                System.err.println("possibleMove: " + possibleMove);
            }

            if (gamePhase.equals("MOVE")) {
                System.out.println("MOVE " + myPlayer.nextZone());
            } else if (gamePhase.equals("RELEASE")) {
                boolean isRelease = false;
                int bestRelease = 99;
                Application bestApplication = null;
                for (Application application : applicationList) {
                    int debtsAfterPlay = application.technicalDebtsAfterPlay(hand);
                    if (debtsAfterPlay < 2 && debtsAfterPlay < bestRelease) {
                        isRelease = true;
                        bestRelease = debtsAfterPlay;
                        bestApplication = application;

                    }
                }
                if (!isRelease) {

                    System.out.println("WAIT");
                } else {
                    System.out.println("RELEASE " + bestApplication.getId());
                }
            } else {
                System.out.println("WAIT");

            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // In the first league: RANDOM | MOVE <zoneId> | RELEASE <applicationId> | WAIT; In later leagues: | GIVE <cardType> | THROW <cardType> | TRAINING | CODING | DAILY_ROUTINE | TASK_PRIORITIZATION <cardTypeToThrow> <cardTypeToTake> | ARCHITECTURE_STUDY | CONTINUOUS_DELIVERY <cardTypeToAutomate> | CODE_REVIEW | REFACTORING;
        }
    }
}