/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.is103t4.corendonluggagesystem.scenes;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import me.is103t4.corendonluggagesystem.LugSysMain;

/**
 * An enumeration holding all the possible scenes that can be displayed on the primary
 * stage
 *
 * @author Finn Bon
 */
public enum Scenes {

    LOGIN("Login"),
    PASSWORD_RECOVERY("RecoverPassword"),
    RECOVERY_CONFIRMATION("PasswordReset"),
    /*MAIN("MainFrame", "main")*/;

    private Pane root;
    private Controller controller;
    private String fxmlURL;

    private Scenes(String name, String pckg) {
	fxmlURL = "/fxml/" + (pckg.length() == 0 ? "" : pckg + "/") + name + "Interface.fxml";
	System.out.println(fxmlURL);
    }

    public boolean initialize(LugSysMain main) {
	try {
	    FXMLLoader loader = new FXMLLoader(getClass().
		    getResource(fxmlURL));

	    root = loader.load();
	    controller = (Controller) loader.getController();
	    Scene sc = main.getStage().
		    getScene();
	    scene = sc;
	    if (controller != null) {
		controller.setScene(scene);
		controller.setMain(main);
		controller.postInit();
	    }
	    return true;
	} catch (IOException exc) {
	    exc.printStackTrace();
	    return false;
	}
    }

    public Controller getController() {
	return controller;
    }

    public Pane getRoot() {
	return root;
    }

    public void setToScene() {
	scene.setRoot(root);
    }

    private Scenes(String name) {
	this(name, "");
    }

    private static Scene scene;
    
    public static void initScene(Scene scene) {
	Scenes.scene = scene;
    }
    
    public static void initAll(LugSysMain lugSysMain) {
	for (Scenes scene : values()) {
	    scene.initialize(lugSysMain);
	}
    }

}
