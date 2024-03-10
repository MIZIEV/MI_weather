package prog.view;

public class SecondWindow {
/*
    private final CityName cityName;
    private final FirstWindow firstWindow;
    private final JSONParser parser;
    //____________________________________________________SC - style class, TB - toggle button
    private final static String WINDOW_TITLE = "MI weather";
    private final static String WINDOW_ICON_URL = "Icons/weather_icon.png";
    private final static String SC_SIDE_BAR = "left-VBox";
    private final static String SC_LABEL = "Label";
    private final static String SC_LEFT_VBOX_BLOCK = "Left-block-VBox";
    private final static String SC_VBOX_BLOCK = "VBox-block";
    private final static String SC_TB_DIAGRAM = "Toggle-Button-diagram";
    private final static String SC_TB_INFO = "Toggle-Button-info";
    private final static String SC_TB_DB_INFO = "Toggle-Button-DB-info";
    private final static String SC_BACK_BUTTON = "Back-Button";
    private final static String SC_GOOGLE_MAP_BUTTON = "Google-Button";
    //____________________________________________________window size constants
    private final static short WINDOW_WIDTH = 1366;
    private final static short WINDOW_HEIGHT = 768;
    private final static short WINDOW_MIN_WIDTH = 880;
    private final static short WINDOW_MIN_HEIGHT = 550;
   // private final static short WINDOW_MAX_WIDTH = 1050;
   // private final static short WINDOW_MAX_HEIGHT = 750;
    private final static short SIDEBAR_WIDTH = 250;
    //____________________________________________________other elements constants
    private final static short IMAGE_SIZE = 150;

    private final static short VBOX_BLOCK_WIDTH = 200;
    private final static short VBOX_BLOCK_HEIGHT = 150;
    private final static byte VBOX_BLOCK_SPICING = 10;

    private final static byte LEFT_BLOCK_HBOX_TEMP_SPACING = 10;
    private final static byte MARGIN = 20;
    private final static byte MARGIN_FOR_COLLECTIONS_BLOCKS = 20;

    private final static short TB_WIDTH = 140;
    private final static short TB_HEIGHT = 50;
    private final static short LEFT_INFO_BLOCK_WIDTH = 830;

    public SecondWindow(CityName name, JSONParser jsonParser, FirstWindow firstW) {
        this.cityName = name;
        this.parser = jsonParser;
        this.firstWindow = firstW;
    }

    public void startWin(String theme) {
        String winStylesheet = getClass().getResource(theme + "SecondWindow.css").toExternalForm();
        Insets margin = new Insets(MARGIN);

        Stage secondWindow = new Stage();
        GoogleMap googleMap = new GoogleMap();
        WeatherImage weatherImage = new WeatherImage(parser);
        PresentTime time = new PresentTime();
        TimeController timeController = new TimeController(time);
        DataAnalyser analyser = new DataAnalyser();
        DetailInfo detailInfo = new DetailInfo(parser);
        Diagram diagram = new Diagram(parser, analyser);
       /* DBWorker dbWorker = new DBWorker(parser);
        dbWorker.setDataToDB();
        dbWorker.getDataFromDB();
        ____________________________________________________creating the main window components
        BorderPane mainPane = new BorderPane();
        BorderPane topPane = new BorderPane();
        BorderPane centralPane = new BorderPane();
        HBox collectionBlocks = new HBox(MARGIN_FOR_COLLECTIONS_BLOCKS);
        collectionBlocks.setAlignment(Pos.CENTER);
        //____________________________________________________creating left VBox information block with all elements
        VBoxPattern leftInfoBlock = new VBoxPattern(LEFT_INFO_BLOCK_WIDTH, SC_LEFT_VBOX_BLOCK);
        leftInfoBlock.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(leftInfoBlock, margin);
        HBox temperatureHBox = new HBox(LEFT_BLOCK_HBOX_TEMP_SPACING);
        temperatureHBox.setAlignment(Pos.TOP_CENTER);
        String cityN = cityName.getCityName();
        LabelPattern nameOfTheCity = new LabelPattern(cityN, SC_LABEL);
        LabelPattern temperature = new LabelPattern("Temperature", SC_LABEL);
        LabelPattern min = new LabelPattern(parser.getTodayIndex(), "min ", parser, analyser, SC_LABEL);
        LabelPattern max = new LabelPattern(parser.getTodayIndex(), "max ", parser, analyser, SC_LABEL);
        LabelPattern tempAnswer = new LabelPattern(parser.getTempNow(), SC_LABEL);
        LabelPattern clouds = new LabelPattern(parser.getWeatherNow(), SC_LABEL);
        temperatureHBox.getChildren().addAll(min, max);
        leftInfoBlock.getChildren().addAll(nameOfTheCity, temperature, tempAnswer, temperatureHBox, clouds);
        //____________________________________________________creating all info blocks
        VBoxBlock firstBlock = new VBoxBlock(VBOX_BLOCK_SPICING, VBOX_BLOCK_WIDTH, VBOX_BLOCK_HEIGHT, Pos.CENTER, SC_VBOX_BLOCK);
        firstBlock.arrangeElements(parser.getTodayDate(),parser.getWeatherToday(),parser.getTodayIndex(), parser, analyser);
        VBoxBlock secondBlock = new VBoxBlock(VBOX_BLOCK_SPICING, VBOX_BLOCK_WIDTH, VBOX_BLOCK_HEIGHT, Pos.CENTER, SC_VBOX_BLOCK);
        secondBlock.arrangeElements(parser.getDateSecondDay(),parser.getWeatherSecondDay(),parser.getSecondDayIndex(), parser, analyser);
        VBoxBlock thirdBlock = new VBoxBlock(VBOX_BLOCK_SPICING, VBOX_BLOCK_WIDTH, VBOX_BLOCK_HEIGHT, Pos.CENTER, SC_VBOX_BLOCK);
        thirdBlock.arrangeElements(parser.getDateThirdDay(),parser.getWeatherThirdDay(),parser.getThirdDayIndex(), parser, analyser);
        VBoxBlock fourBlock = new VBoxBlock(VBOX_BLOCK_SPICING, VBOX_BLOCK_WIDTH, VBOX_BLOCK_HEIGHT, Pos.CENTER, SC_VBOX_BLOCK);
        fourBlock.arrangeElements(parser.getDateFourDay(),parser.getWeatherFourthDay(),parser.getFourDayIndex(), parser, analyser);
        VBoxBlock fifthBlock = new VBoxBlock(VBOX_BLOCK_SPICING, VBOX_BLOCK_WIDTH, VBOX_BLOCK_HEIGHT, Pos.CENTER, SC_VBOX_BLOCK);
        fifthBlock.arrangeElements(parser.getDateFifthDay(),parser.getWeatherFifthDay(),parser.getFifthDayIndex(), parser, analyser);
        collectionBlocks.getChildren().addAll(firstBlock, secondBlock, thirdBlock, fourBlock, fifthBlock);

        mainPane.getStyleClass().add("pane");
        //____________________________________________________creating left side bar with toggle buttons
        VBoxPattern leftTopSideBar = new VBoxPattern(SIDEBAR_WIDTH, SC_SIDE_BAR);
        BorderPane imagePane = new BorderPane();
        VBoxPattern.setMargin(imagePane, margin);
        imagePane.setPrefSize(IMAGE_SIZE, IMAGE_SIZE);
        imagePane.getStyleClass().add("image-pane");

        imagePane.setCenter(weatherImage.getTodayImage(timeController.getDayOrNight(), theme));
        VBoxPattern leftBottomSideBar = new VBoxPattern(SIDEBAR_WIDTH, SC_SIDE_BAR);
        leftBottomSideBar.setSpacing(MARGIN);
        leftBottomSideBar.setAlignment(Pos.BOTTOM_CENTER);

        ButtonsPattern mapButton = new ButtonsPattern(TB_WIDTH, TB_HEIGHT, SC_GOOGLE_MAP_BUTTON);
        ButtonsPattern backButton = new ButtonsPattern(TB_WIDTH, TB_HEIGHT, SC_BACK_BUTTON);

        ToggleGroup leftButtonsGroup = new ToggleGroup();
        ToggleButtonPattern diagramButton = new ToggleButtonPattern(TB_WIDTH, TB_HEIGHT, SC_TB_DIAGRAM);
        ToggleButtonPattern detailButton = new ToggleButtonPattern(TB_WIDTH, TB_HEIGHT, SC_TB_INFO);
        ToggleButtonPattern DBInfoButton = new ToggleButtonPattern(TB_WIDTH, TB_HEIGHT, SC_TB_DB_INFO);

        diagramButton.setToggleGroup(leftButtonsGroup);
        detailButton.setToggleGroup(leftButtonsGroup);
        DBInfoButton.setToggleGroup(leftButtonsGroup);
        leftTopSideBar.getChildren().addAll(imagePane, leftInfoBlock);
        leftBottomSideBar.getChildren().addAll(mapButton, DBInfoButton, diagramButton, detailButton, backButton);

        BorderPane.setMargin(collectionBlocks, margin);
        topPane.setLeft(leftTopSideBar);
        topPane.setCenter(collectionBlocks);
        mainPane.setTop(topPane);
        //____________________________________________________creating all controllers
        InfoButtonsControllers todayController = new TodayInfoController(diagram, parser);
        InfoButtonsControllers tomorrowController = new SecondDayInfoController(diagram, parser);
        InfoButtonsControllers afterTomorrowController = new ThirdDayInfoController(diagram, parser);
        InfoButtonsControllers fourDayController = new FourDayInfoController(diagram, parser);
        InfoButtonsControllers fifthDayController = new FifthDayInfoController(diagram, parser);

        TodayInfoControl todayInfoControl = new TodayInfoControl(detailInfo, parser, analyser);
        SecondDayInfControl secondDayInfControl = new SecondDayInfControl(detailInfo, parser, analyser);
        ThirdDayInfControl thirdDayInfControl = new ThirdDayInfControl(detailInfo, parser, analyser);
        FourDayInfControl fourDayInfControl = new FourDayInfControl(detailInfo, parser, analyser);
        FifthDayControl fifthDayControl = new FifthDayControl(detailInfo, parser, analyser);
        //____________________________________________________inside VBox buttons listeners
        firstBlock.getInfo().setOnAction(event -> {
            if (diagramButton.isSelected()) todayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                todayInfoControl.putDataToPane();
                centralPane.getChildren().clear();
                todayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });
        secondBlock.getInfo().setOnAction(event -> {
            if (diagramButton.isSelected()) tomorrowController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                secondDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                tomorrowController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });
        thirdBlock.getInfo().setOnAction(event -> {
            if (diagramButton.isSelected()) afterTomorrowController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                thirdDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                afterTomorrowController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });
        fourBlock.getInfo().setOnAction(event -> {
            if (diagramButton.isSelected()) fourDayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                fourDayInfControl.putDataToPane();
                centralPane.getChildren().clear();
                fourDayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });
        fifthBlock.getInfo().setOnAction(event -> {
            if (diagramButton.isSelected()) fifthDayController.putDataToDiagram();
            else if (detailButton.isSelected()) {
                fifthDayControl.putDataToPane();
                centralPane.getChildren().clear();
                fifthDayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });
        //____________________________________________________toggle buttons listeners
        diagramButton.setOnAction(event -> {
            if (diagramButton.isSelected()) {
                centralPane.getChildren().clear();
                centralPane.setCenter(diagram.showDiagram(theme));
            }
        });
        detailButton.setOnAction(event -> {
            if (detailButton.isSelected()) {
                todayInfoControl.putDataToPane();
                centralPane.getChildren().clear();
                fifthDayController.clearDataFromDiagram(diagram);
                centralPane.setCenter(detailInfo.showInfo(theme));
            }
        });

/*        DBInfoButton.setOnAction(event -> {
            DBInfo dbInfo = new DBInfo(dbWorker);
            centralPane.getChildren().clear();
            fifthDayController.clearDataFromDiagram(diagram);
            centralPane.setCenter(dbInfo.showInfo(theme));
        });
        //____________________________________________________buttons listeners
        mapButton.setOnAction(event -> googleMap.launchMap(cityName.getCityName()));

        backButton.setOnAction(event -> {
            secondWindow.close();
            BackButtonController controller = new BackButtonController(parser);
            controller.deletingTwoValues();
            firstWindow.startWin();
        });
        diagramButton.fire();   //<-------- selected button by default

        BorderPane.setMargin(centralPane, margin);
        mainPane.setCenter(centralPane);
        mainPane.setLeft(leftBottomSideBar);

        Scene secondWindowScene = new Scene(mainPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        secondWindowScene.getStylesheets().add(winStylesheet);
        secondWindow.getIcons().add(new Image(WINDOW_ICON_URL));
        secondWindow.setMinWidth(WINDOW_MIN_WIDTH);
        secondWindow.setMinHeight(WINDOW_MIN_HEIGHT);
       // secondWindow.setMaxWidth(WINDOW_MAX_WIDTH);
       // secondWindow.setMaxHeight(WINDOW_MAX_HEIGHT);
        secondWindow.setScene(secondWindowScene);
        secondWindow.setTitle(WINDOW_TITLE);
        new FadeIn(mainPane).play();
        secondWindow.show();
    }*/
}