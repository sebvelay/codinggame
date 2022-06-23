package com.greenit.game;

public class Card {


    private final String cardsLocation;
    private final int trainingCardsCount;
    private final int codingCardsCount;
    private final int dailyRoutineCardsCount;
    private final int taskPrioritizationCardsCount;
    private final int architectureStudyCardsCount;
    private final int continuousDeliveryCardsCount;
    private final int codeReviewCardsCount;
    private final int refactoringCardsCount;
    private final int bonusCardsCount;
    private final int technicalDebtCardsCount;

    public Card(String cardsLocation, int trainingCardsCount, int codingCardsCount, int dailyRoutineCardsCount, int taskPrioritizationCardsCount, int architectureStudyCardsCount, int continuousDeliveryCardsCount, int codeReviewCardsCount, int refactoringCardsCount, int bonusCardsCount, int technicalDebtCardsCount) {
        this.cardsLocation = cardsLocation;
        this.trainingCardsCount = trainingCardsCount;
        this.codingCardsCount = codingCardsCount;
        this.dailyRoutineCardsCount = dailyRoutineCardsCount;
        this.taskPrioritizationCardsCount = taskPrioritizationCardsCount;
        this.architectureStudyCardsCount = architectureStudyCardsCount;
        this.continuousDeliveryCardsCount = continuousDeliveryCardsCount;
        this.codeReviewCardsCount = codeReviewCardsCount;
        this.refactoringCardsCount = refactoringCardsCount;
        this.bonusCardsCount = bonusCardsCount;
        this.technicalDebtCardsCount = technicalDebtCardsCount;

    }

    public void debug(){
        System.err.println("cardsLocation: " + cardsLocation +"   trainingCardsCount: " + trainingCardsCount + "   codingCardsCount: " + codingCardsCount + "   dailyRoutineCardsCount: " + dailyRoutineCardsCount + "   taskPrioritizationCardsCount: " + taskPrioritizationCardsCount + "   architectureStudyCardsCount: " + architectureStudyCardsCount + "   continuousDeliveryCardsCount: " + continuousDeliveryCardsCount + "   codeReviewCardsCount: " + codeReviewCardsCount + "   refactoringCardsCount: " + refactoringCardsCount + "   bonusCardsCount: " + bonusCardsCount + "   technicalDebtCardsCount: " + technicalDebtCardsCount);
    }

    public int getTrainingCardsCount() {

        return trainingCardsCount;
    }

    public int getCodingCardsCount() {
        return codingCardsCount;
    }

    public int getDailyRoutineCardsCount() {
        return dailyRoutineCardsCount;
    }

    public int getTaskPrioritizationCardsCount() {
        return taskPrioritizationCardsCount;
    }

    public int getArchitectureStudyCardsCount() {
        return architectureStudyCardsCount;
    }

    public int getContinuousDeliveryCardsCount() {
        return continuousDeliveryCardsCount;
    }

    public int getCodeReviewCardsCount() {
        return codeReviewCardsCount;
    }

    public int getRefactoringCardsCount() {
        return refactoringCardsCount;
    }

    public int getBonus() {
        return bonusCardsCount;
    }
}
