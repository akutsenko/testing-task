package common.reporting;

import io.qameta.allure.Step;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepsLogger implements StepLifecycleListener {

    @Override
    public void beforeStepStart(final StepResult result) {
        log.info(result.getName());
    }

    @Step("{message}")
    public static void log(String message) {
    }

}
