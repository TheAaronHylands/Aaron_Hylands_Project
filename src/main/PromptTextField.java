package main;

import java.awt.Color;

/* Since regular Java swing TextFields don't support prompts I had to find another solution.
 * 
 * I did not make this class myself and instead used a solution that I found at:
 * https://stackoverflow.com/questions/11200585/adding-a-prompt-text-property-to-jtextfield
 * 
 */

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PromptTextField extends JTextField {
	
	public static Color promptIvory = new Color(145, 145, 140);
	
	public void setFontColorToPromptColor() {
		this.setForeground(promptIvory);
	}
	public void setFontColorToRegularColor() {
		this.setForeground(CrapsUI.ivory);
	}
	
    public PromptTextField(final String promptText) {
        super(promptText);
        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
            	
                if(getText().isEmpty()) {
                    setText(promptText);
                    setFontColorToPromptColor();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
            	setFontColorToRegularColor();
                if(getText().equals(promptText)) {
                    setText("");
                }
            }
        });

    }

}