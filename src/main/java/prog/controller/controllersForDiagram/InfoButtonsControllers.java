package prog.controller.controllersForDiagram;

import prog.view.Diagram;

public interface InfoButtonsControllers {
    void putDataToDiagram();

    default void clearDataFromDiagram(Diagram diagram){
        diagram.getTemp().getData().clear();
    }
}