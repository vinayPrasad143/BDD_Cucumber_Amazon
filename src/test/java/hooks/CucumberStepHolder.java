package hooks;

public class CucumberStepHolder {
    private static final ThreadLocal<String> currentStep = new ThreadLocal<>();

    public static void setCurrentStepText(String step) {
        currentStep.set(step);
    }

    public static String getCurrentStepText() {
        return currentStep.get();
    }
}
