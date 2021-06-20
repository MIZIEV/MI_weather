package Controller.ControllersForDiagram;

import View.Diagram;

public interface InfoButtonsControllers {
    void putDataToDiagram();

    default void clearDataFromDiagram(Diagram diagram){
        diagram.getTemp().getData().clear();
    }
}