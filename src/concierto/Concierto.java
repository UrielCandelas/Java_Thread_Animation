/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package concierto;

import javax.swing.JFrame;

/**
 *
 * @author UrielC
 */
public class Concierto extends JFrame{

    public Concierto(){
        
       setTitle("Concierto");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setSize(1050,750);
       setLocationRelativeTo(null);
       setResizable(false);
       add(new Tablero());
       setVisible(true);
    }
    public static void main(String[] args) {
        new Concierto();
    }
    
}
