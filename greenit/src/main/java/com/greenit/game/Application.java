package com.greenit.game;

import java.util.List;

public class Application {

    private final int id;
    private final String objectType;
    private final int trainingNeeded;
    private final int codingNeeded;
    private final int dailyRoutineNeeded;
    private final int taskPrioritizationNeeded;
    private final int architectureStudyNeeded;
    private final int continuousDeliveryNeeded;
    private final int codeReviewNeeded;
    private final int refactoringNeeded;

    public Application(int id, String objectType, int trainingNeeded, int codingNeeded, int dailyRoutineNeeded, int taskPrioritizationNeeded, int architectureStudyNeeded, int continuousDeliveryNeeded, int codeReviewNeeded, int refactoringNeeded) {


        this.id = id;
        this.objectType = objectType;
        this.trainingNeeded = trainingNeeded;
        this.codingNeeded = codingNeeded;
        this.dailyRoutineNeeded = dailyRoutineNeeded;
        this.taskPrioritizationNeeded = taskPrioritizationNeeded;
        this.architectureStudyNeeded = architectureStudyNeeded;
        this.continuousDeliveryNeeded = continuousDeliveryNeeded;
        this.codeReviewNeeded = codeReviewNeeded;
        this.refactoringNeeded = refactoringNeeded;


    }

    public int getId() {
        return id;
    }

    public void debug() {
        System.err.println("id: " + id + "   objectType: " + objectType + "   trainingNeeded: " + trainingNeeded + "   codingNeeded: " + codingNeeded + "   dailyRoutineNeeded: " + dailyRoutineNeeded + "   taskPrioritizationNeeded: " + taskPrioritizationNeeded + "   architectureStudyNeeded: " + architectureStudyNeeded + "   continuousDeliveryNeeded: " + continuousDeliveryNeeded + "   codeReviewNeeded: " + codeReviewNeeded + "   refactoringNeeded: " + refactoringNeeded);
    }

    public int technicalDebtsAfterPlay(List<Card> cards) {
        int technicalDebts = 0;
        for (Card card : cards) {

            int trainingDebt = trainingNeeded - card.getTrainingCardsCount() * 2;
            if (trainingDebt < 0) {
                trainingDebt = 0;
            }
            technicalDebts += trainingDebt;

            int codingDebt = codingNeeded - card.getCodingCardsCount() * 2;
            if (codingDebt < 0) {
                codingDebt = 0;
            }
            technicalDebts += codingDebt;

            int dailyRoutineDebt = dailyRoutineNeeded - card.getDailyRoutineCardsCount() * 2;
            if (dailyRoutineDebt < 0) {
                dailyRoutineDebt = 0;
            }
            technicalDebts += dailyRoutineDebt;

            int taskPrioritizationDebt = taskPrioritizationNeeded - card.getTaskPrioritizationCardsCount() * 2;
            if (taskPrioritizationDebt < 0) {
                taskPrioritizationDebt = 0;
            }
            technicalDebts += taskPrioritizationDebt;

            int architectureStudyDebt = architectureStudyNeeded - card.getArchitectureStudyCardsCount() * 2;
            if (architectureStudyDebt < 0) {
                architectureStudyDebt = 0;
            }
            technicalDebts += architectureStudyDebt;

            int continuousDeliveryDebt = continuousDeliveryNeeded - card.getContinuousDeliveryCardsCount() * 2;
            if (continuousDeliveryDebt < 0) {
                continuousDeliveryDebt = 0;
            }
            technicalDebts += continuousDeliveryDebt;

            int codeReviewDebt = codeReviewNeeded - card.getCodeReviewCardsCount() * 2;
            if (codeReviewDebt < 0) {
                codeReviewDebt = 0;
            }
            technicalDebts += codeReviewDebt;

            int refactoringDebt = refactoringNeeded - card.getRefactoringCardsCount() * 2;
            if (refactoringDebt < 0) {
                refactoringDebt = 0;
            }
            technicalDebts += refactoringDebt;

            int bonus = card.getBonus();
            technicalDebts -= bonus;

        }
        return technicalDebts;

    }
}
