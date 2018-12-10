/*******************************************************************************
 * Copyright (c) 2010-2017, IncQueryLabs Ltd.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 *     Peter Lunk - initial API and implementation 
 *******************************************************************************/
package hu.bme.mit.massif.simulink.cli;

import java.io.File;

import org.eclipse.viatra.query.runtime.api.ViatraQueryEngineOptions;
import org.eclipse.viatra.query.runtime.localsearch.matcher.integration.LocalSearchEMFBackendFactory;
import org.eclipse.viatra.query.runtime.rete.matcher.ReteBackendFactory;

import br.com.embraer.massif.commandevaluation.commands.MatlabController;
import hu.bme.mit.massif.communication.ICommandEvaluator;
import hu.bme.mit.massif.communication.command.MatlabCommandFactory;
import hu.bme.mit.massif.simulink.SimulinkModel;
import hu.bme.mit.massif.simulink.api.Exporter;
import hu.bme.mit.massif.simulink.api.exception.SimulinkApiException;
import hu.bme.mit.massif.simulink.cli.util.CLIInitializationUtil;
import hu.bme.mit.massif.simulink.cli.util.CLISimulinkAPILogger;

/**
 * This class provides functions to load Massif EMF models into MATLAB
 * 
 * @author Peter Lunk
 */
public abstract class CLIMatlabCreator {

    public abstract ICommandEvaluator getEvaluator();

    protected boolean debugMode = false;

    public CLIMatlabCreator() {
        ViatraQueryEngineOptions.setSystemDefaultBackends(ReteBackendFactory.INSTANCE, ReteBackendFactory.INSTANCE,
                LocalSearchEMFBackendFactory.INSTANCE);
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void createMatlabModel(String modelName, String modelPath) throws SimulinkApiException, InterruptedException {
        createMatlabModel(modelName, modelPath, "slx");
    }

    public void createMatlabModel(String modelName, String modelPath, String extension) throws SimulinkApiException, InterruptedException {

        CLIInitializationUtil.setupEnvironment();
        CLISimulinkAPILogger logger = new CLISimulinkAPILogger();

        MatlabController controller = new MatlabController();
        controller.setDebug(debugMode);
        ICommandEvaluator evaluator = getEvaluator();
        Exporter exporter = new Exporter(logger);
        SimulinkModel loadedModel;
        logger.debug("Loading Simulunk model...");
        loadedModel = exporter.loadSimulinkModel("file:/" + modelPath + File.separator + modelName);
        logger.debug("Simulink model loaded");
        MatlabCommandFactory commandFactory = new MatlabCommandFactory(evaluator);
        logger.debug("Loading model into MATLAB...");

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    exporter.export(loadedModel, commandFactory);

                    String fqn = loadedModel.getSimulinkRef().getFQN();
                    exporter.saveSimulinkModel(fqn, extension);
                } catch (SimulinkApiException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();

        logger.debug("Model loaded into MATLAB");
    }

}
