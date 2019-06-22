package sp.osgi.swtclient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import sp.osgi.contador.servicio.Contador;

public class SWTWindow extends Thread {

	private Shell shell;

	private Display display;

	boolean quit = false;

	private Contador contador;

	public SWTWindow(Contador contador) {
		super();
		this.contador = contador;
	}

	public void run() {
		createShell();
		shell.open();
		display = shell.getDisplay();
		while (!shell.isDisposed() && !quit)
			try {
				if (!display.readAndDispatch())
					display.sleep();
			} catch (Exception e) {
				e.printStackTrace();

			}

		if (!shell.isDisposed())
			shell.dispose();
		if (!display.isDisposed())
			display.dispose();

	}

	private void createShell() {
		FormData data = null;
		shell = new Shell();
		shell.setText("Contador SWT");
		FormLayout layout = new FormLayout();
		shell.setSize(350, 180);
		shell.setLayout(layout);

		Group group = new Group(shell, SWT.NONE);
		group.setText("Cantidad a Incrementar:");
		group.setLayout(new FormLayout());
		data = new FormData();
		data.top = new FormAttachment(10, 0);
		data.left = new FormAttachment(10, 0);
		data.right = new FormAttachment(90, 0);
		data.bottom = new FormAttachment(50, 0);
		group.setLayoutData(data);

		final Spinner spinner = new Spinner(group, SWT.BORDER);
		spinner.setMinimum(0);
		spinner.setMaximum(1000);
		spinner.setSelection(1);
		spinner.setIncrement(1);
		spinner.setPageIncrement(100);
		data = new FormData();
		data.top = new FormAttachment(25, 0);
		data.left = new FormAttachment(10, 10);
		spinner.setLayoutData(data);

		Button button = new Button(group, SWT.PUSH);
		button.setText("Invocar contador");
		data = new FormData();
		data.top = new FormAttachment(25, 0);
		data.left = new FormAttachment(spinner, 10);
		button.setLayoutData(data);

		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("Valor contador:");
		data = new FormData();
		data.top = new FormAttachment(group, 20);
		data.left = new FormAttachment(10, 10);
		label2.setLayoutData(data);

		final Text text = new Text(shell, SWT.READ_ONLY | SWT.BORDER);
		text.setFont(new Font(display, "Arial", 20, SWT.BOLD));
		text.setText("" + contador.getValue());
		data = new FormData();
		data.top = new FormAttachment(group, 20);
		data.left = new FormAttachment(label2, 10);
		data.right = new FormAttachment(90, 0);
		text.setLayoutData(data);

		Link link = new Link(shell, SWT.NONE);
		link.setText("<A>[Refrescar]</A>");
		data = new FormData();
		data.top = new FormAttachment(label2, 16);
		data.left = new FormAttachment(10, 10);
		link.setLayoutData(data);

		// eventos
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				contador.incr(spinner.getSelection());
				text.setText("" + contador.getValue());
			}
		});
		link.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				text.setText("" + contador.getValue());
			}
		});
	}

	public void close() {
		display.asyncExec(new Runnable() {
			public void run() {
				if (!shell.isDisposed())
					shell.dispose();
			}
		});

	}

}
