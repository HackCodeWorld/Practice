package OOP.interface_example.cellmachine;

import javax.swing.JFrame;

import OOP.interface_example.cell.Cell;
import OOP.interface_example.field.Field;
import OOP.interface_example.field.View;

public class CellMachine {

	public static void main(String[] args) {
		Field field = new Field(30,30);
		// prepare data
		for ( int row = 0; row<field.getHeight(); row++ ) {
			for ( int col = 0; col<field.getWidth(); col++ ) {
				// initialized cell but not definitively lived
				field.place(row, col, new Cell());
			}
		}
		for ( int row = 0; row<field.getHeight(); row++ ) {
			for ( int col = 0; col<field.getWidth(); col++ ) {
				Cell cell = field.get(row, col);
				// 1 / 5 cell reborn
				if ( Math.random() < 0.2 ) {
					cell.reborn();
				}
			}
		}

		View view = new View(field);
		JFrame frame = new JFrame(); // GUI window
		// x red button can close the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// some settings
		frame.setResizable(false);
		frame.setTitle("Cells");
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
		
		for ( int i=0; i<1000; i++ ) {
			for ( int row = 0; row<field.getHeight(); row++ ) {
				for ( int col = 0; col<field.getWidth(); col++ ) {
					Cell cell = field.get(row, col);
					Cell[] neighbour = field.getNeighbour(row, col);
					int numOfLive = 0;
					for ( Cell c : neighbour ) {
						if ( c.isAlive() ) {
							numOfLive++;
						}
					}
					System.out.print("["+row+"]["+col+"]:");
					System.out.print(cell.isAlive()?"live":"dead");
					System.out.print(":"+numOfLive+"-->");
					if ( cell.isAlive() ) {
						if ( numOfLive <2 || numOfLive >3 ) {
							cell.die();
							System.out.print("die");
						}
					} else if ( numOfLive == 3 ) {
						cell.reborn();
						System.out.print("reborn");
					}
					System.out.println();
				}
			}
			System.out.println("UPDATE");
			frame.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
