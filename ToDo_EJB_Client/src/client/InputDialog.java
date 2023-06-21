package client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class InputDialog extends Dialog {
  private String message;

  private String input;

  public InputDialog(Shell parent) {
    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
  }

  public InputDialog(Shell parent, int style) {
    super(parent, style);
    setText("Input Dialog");
    setMessage("Title");
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public String open() {
    Shell shell = new Shell(getParent(), getStyle());
    shell.setText(getText());
    createContents(shell);
    shell.pack();
    shell.open();
    Display display = getParent().getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    return input;
  }

  private void createContents(final Shell shell) {
    shell.setLayout(new GridLayout(2, true));

    Label labelTitle = new Label(shell, SWT.NONE);
    labelTitle.setText("Title: ");
    GridData data = new GridData();
    Text inputTitle = new Text(shell, SWT.SINGLE); 
    data.horizontalSpan = 2; 
    labelTitle.setLayoutData(data);
    inputTitle.setLayoutData(data);

    Button ok = new Button(shell, SWT.PUSH);
    ok.setText("OK");
    data = new GridData(GridData.FILL_HORIZONTAL);
    ok.setLayoutData(data);
    ok.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        input = inputTitle.getText();
        shell.close();
      }
    });

    Button cancel = new Button(shell, SWT.PUSH);
    cancel.setText("Cancel");
    data = new GridData(GridData.FILL_HORIZONTAL);
    cancel.setLayoutData(data);
    cancel.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        input = null;
        shell.close();
      }
    });

    shell.setDefaultButton(ok);
  }
}

