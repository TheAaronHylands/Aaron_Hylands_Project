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

//This class "extends" JTextField meaning that it becomes a subclass of it and inherits its methods and variables
public class PromptTextField extends JTextField {
			//Small error with not providing serialVersionUID, I'm not using serialization in this program so it's safe to ignore.
	
	public static Color promptIvory = new Color(145, 145, 140);
	
	public void setFontColorToPromptColor() {//This gives the prompt text a prompt-like color
		this.setForeground(promptIvory);
	}
	public void setFontColorToRegularColor() {//This sets the text color back to the regular color 
		this.setForeground(CrapsUI.ivory);
	}
	
    public PromptTextField(final String promptText) {// This is the constructor
        super(promptText);
        addFocusListener(new FocusListener() {//This is an event listener that looks for when the text field has been clicked on

            @Override //This tells the superclass that it's function is being overridden
            public void focusLost(FocusEvent e) {	//This executes when the text field is clicked away from,
            										// it also sets the text to the prompt text if the field is empty
            	
                if(getText().isEmpty()) {
                    setText(promptText);
                    setFontColorToPromptColor();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {	//This executes when the text field is clicked on,
            										// it also sets the text to empty if the contents are the prompt
            	setFontColorToRegularColor();
                if(getText().equals(promptText)) {
                    setText("");
                }
            }
        });

    }

}