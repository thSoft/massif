/*******************************************************************************
 * Copyright (c) 2010-2013, Embraer S.A., Budapest University of Technology and Economics
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 *     Marton Bur, Abel Hegedus, Akos Horvath - initial API and implementation 
 *******************************************************************************/
package hu.bme.mit.massif.communication.datavisitor;

import java.util.Arrays;
import java.util.Map;

import hu.bme.mit.massif.communication.datatype.CellMatlabData;
import hu.bme.mit.massif.communication.datatype.Handle;
import hu.bme.mit.massif.communication.datatype.IVisitableMatlabData;
import hu.bme.mit.massif.communication.datatype.Logical;
import hu.bme.mit.massif.communication.datatype.MatlabString;
import hu.bme.mit.massif.communication.datatype.StructMatlabData;

/**
 * Visitor implementation for command string creation
 */
public class ParameterVisitor implements IMatlabDataVisitor {

    // private MatlabCommand command;
    private String[] commandStrings;

    public String[] getCommandStrings() {
        return Arrays.copyOf(commandStrings, commandStrings.length);
    }

    public ParameterVisitor(String[] commandStrings) {
        this.commandStrings = Arrays.copyOf(commandStrings, commandStrings.length);
    }

    @Override
    public void visit(CellMatlabData compositeData) {
        if (compositeData.size() == 0) {
            return;
        }
        String[] newCommandStrings = new String[commandStrings.length * compositeData.getDatas().size()];
        for (int i = 0; i < commandStrings.length; i++) {
            for (int j = 0; j < compositeData.getDatas().size(); j++) {
                newCommandStrings[i * compositeData.getDatas().size() + j] = commandStrings[i]
                        + compositeData.getDatas().get(j) + ",";
            }
        }
        commandStrings = newCommandStrings;
    }

    @Override
    public void visit(Handle handle) {
        for (int i = 0; i < commandStrings.length; i++) {
            commandStrings[i] = commandStrings[i] + handle + ",";
        }
    }

    @Override
    public void visit(MatlabString matlabString) {
        for (int i = 0; i < commandStrings.length; i++) {
            commandStrings[i] = commandStrings[i] + matlabString + ",";
        }
    }

	@Override
	public void visit(StructMatlabData structMatlabData) {
		for (int i = 0; i < commandStrings.length; i++) {
			Map<String, IVisitableMatlabData> dataMap = structMatlabData.getDatas();
			// Define and call an anonymous function to create structure. Not optimal solution, but confirms to the current API
			commandStrings[i] = commandStrings[i] + "feval(@() struct(";
			for (String dataMemberName : dataMap.keySet()) {
				commandStrings[i] = commandStrings[i] + "'" + dataMemberName + "'," + dataMap.get(dataMemberName) + ",";
			}
			commandStrings[i] = commandStrings[i].replaceAll(",$", ""); // remove the last unnecessary comma
			commandStrings[i] = commandStrings[i] + ")),";
		}
	}

	@Override
	public void visit(Logical logical) {
        for (int i = 0; i < commandStrings.length; i++) {
            commandStrings[i] = commandStrings[i] + logical + ",";
        }
	}

}
