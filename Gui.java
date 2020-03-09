import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ConnecterIO{
    
    //Creates a button and adds it to the container
    private void addButton(String text, Container container){
        JButton button = new JButton(text);
        button.setAlignmentX (Component.CENTER_ALIGNMENT);
        //Create function to set action for button
        //tempFunction();
        container.add(button);
    }
    
    //creates gui
    public Component createComponents(){                
        //Box Layout
        JLabel title = new JLabel("Connector.io");
        JPanel pane = new JPanel();
        pane.setBorder(BoarderFactory.createEmptyBoarder(30,30,10,30));
        pane.setLayout(new gridLayout(0,1));
        pane.add(title);
        addButton("Player v Player",pane);
        addButton("Player v AI",pane);
        addButton("Quit",pane);
        
        return pane;
    }
    
    public static void main(String args[]){
        JFrame frame = new JFrame("connector.io"); //creates new screen
        ConnecterIO app = new ConnecterIO(); //creates new application, but this doesnt make too much sense
        Component components = app.createComponents(); //creates gui
        
        //displays gui
        frame.pack();
        frame.setVisible(true);
    }
}