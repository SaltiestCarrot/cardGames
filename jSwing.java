import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//jave swing
class jSwing{
    public jSwing(){
      //this creates the frame
      JFrame frame = new JFrame("My First GUI");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(300,300);

      //adds some cool menu selections
      /*JMenuBar mb = new JMenuBar();
      JMenu m1 = new JMenu("File");
      JMenu m2 = new JMenu("Other");
      mb.add(m1);
      mb.add(m2);*/
      
      JButton button = new JButton("Hit");
      button.setBounds(100, 50, 120, 40);
      JButton button1 = new JButton("Stand");
      button1.setBounds(100, 100, 120, 40);
      
      button.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        
          
        }
      });
      
      button1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){

          
        }
      });
      
      // Adds Button to content pane of frame
      //frame.getContentPane().add(BorderLayout.NORTH, button);
      //frame.getContentPane().add(BorderLayout.NORTH, button1);
      //frame.getContentPane().add(BorderLayout.CENTER, mb);
      frame.add(button); frame.add(button1);

      frame.setLayout(null);
      frame.setVisible(true);
    }
}
