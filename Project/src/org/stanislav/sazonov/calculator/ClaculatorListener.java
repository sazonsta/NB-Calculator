package org.stanislav.sazonov.calculator;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.actions.Presenter;

@ActionID(
        category = "File",
        id = "org.stanislav.sazonov.calculator.ClaculatorListener"
)
@ActionRegistration(
        lazy = false,
        displayName = "NOT-USED")
@ActionReference(
        path = "Toolbars/File",
        position = 0)
public final class ClaculatorListener extends AbstractAction implements Presenter.Toolbar {

    @Override
    public Component getToolbarPresenter() {
        return new CalculatorPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //delegated to toolbar
    }
}
