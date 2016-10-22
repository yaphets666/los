package lifegame;


import java.awt.Graphics;
import java.awt.*;
import javax.swing.JPanel;

public class World extends JPanel implements Runnable{
	private final int rows;
	private final int columns;
	private final CellStatus[][] generation1;
	private final CellStatus[][] generation2;
	private CellStatus[][] currentGeneration;
	private CellStatus[][] nextGeneration;
	private volatile boolean isChanging = true; 
	private int generation = 0;
	
	public World(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		speed = 500;
		generation1 = new CellStatus[rows][columns];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				generation1[i][j] = CellStatus.Dead;
			}
		}
		
		generation2 = new CellStatus[rows][columns];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				generation2[i][j] = CellStatus.Dead;
			}
		}
		
		currentGeneration = generation1;
		nextGeneration = generation2;
	}
	

	@Override
	public void run()
	{
		while(true)
		{
			synchronized(this)
			{
				while(isChanging)
				{
					try 
					{
						this.wait();
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				
				repaint();
				sleep(1);
				
				for(int i = 0; i < rows; i++)
				{
					for(int j = 0; j < columns; j++)
					{
						evolve(i, j);
					}
				}
				
				generation++;
				CellStatus[][] temp = null;
				temp = currentGeneration;
				currentGeneration = nextGeneration;
				nextGeneration = temp;
				
				for(int i = 0; i < rows; i++)
				{
					for(int j = 0; j < columns; j++)
					{
						nextGeneration[i][j] = CellStatus.Dead;
					}
				}
			}
		}
	}
	
	@Override
    public void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        
        int amount = 0;
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < columns; j++) 
            {
            	if(currentGeneration[i][j] == CellStatus.Active)
            	{
            		g.fillRect(j * 20, i * 20, 20, 20);
            		amount++;
            	}
            	else
            	{
                    g.drawRect(j * 20, i * 20, 20, 20);            		
            	}
            }
        }
        g.setFont(new Font("ËÎÌו",Font.BOLD,18));
        g.drawString("the amount of alive cells:" + amount, 600, 40);
        g.drawString("        generation:       " + generation, 600, 70);
        
        if(amount == 0 && generation != 0){
			isChanging = true;
        }
    }		
	
	public void setArrow()
	{
		generation = 0;
		arrow = new int[40][50];
		arrow[12][11] = 1;         arrow[12][12] = 1;           arrow[11][12] = 1;            arrow[13][12] = 1;
		arrow[12][13] = 1;         arrow[11][13] = 1;           arrow[10][13] = 1;            arrow[13][13] = 1;            arrow[14][13] = 1;
		arrow[12][14] = 1;         arrow[11][14] = 1;           arrow[9][14] = 1;             arrow[13][14] = 1;            arrow[15][14] = 1;
		arrow[12][15] = 1;    arrow[11][15] = 1;      arrow[13][15] = 1;  	arrow[12][16] = 1;      arrow[11][16] = 1;     arrow[13][16] = 1; 
		arrow[12][17] = 1;    arrow[11][17] = 1;      arrow[13][17] = 1; 	arrow[12][18] = 1;      arrow[11][18] = 1;     arrow[13][18] = 1; 
		arrow[12][19] = 1;    arrow[11][19] = 1;      arrow[13][19] = 1; 	arrow[12][20] = 1;      arrow[11][20] = 1;     arrow[13][20] = 1; 
		arrow[12][21] = 1;    arrow[11][21] = 1;      arrow[13][21] = 1; 	arrow[12][22] = 1;      arrow[11][22] = 1;     arrow[13][22] = 1; 
		setShape(arrow);
	}
	
	public void setSquare()
	{
		generation = 0;
		square = new int[40][50];
		square[12][13] = 1;      square[11][13] = 1;       square[10][13] = 1;         square[13][13] = 1;        square[14][13] = 1;
		square[12][14] = 1;      square[11][14] = 1;       square[10][14] = 1;         square[13][14] = 1;        square[14][14] = 1;
		square[12][15] = 1;      square[11][15] = 1;       square[10][15] = 1;         square[13][15] = 1;        square[14][15] = 1;
		square[12][16] = 1;      square[11][16] = 1;       square[10][16] = 1;         square[13][16] = 1;        square[14][16] = 1;
		square[12][17] = 1;      square[11][17] = 1;       square[10][17] = 1;         square[13][17] = 1;        square[14][17] = 1;
		setShape(square);
	}	
	
	public void setZhuan()
	{
		generation = 0;
		zhuan = new int[40][50];
		zhuan[12][12] = 1;       zhuan[12][13] = 1;        zhuan[13][12] = 1;        zhuan[13][13] = 1;
		setShape(zhuan);
	}
	
	public void setFeng()
	{
		generation = 0;
		feng = new int[40][50];
		feng[10][10] = 1;	   	feng[10][11] = 1;		feng[11][12] = 1;
		feng[12][11] = 1;		feng[12][10] = 1;		feng[11][9] = 1;
		setShape(feng);
	}
	
	public void setZha()
	{
		generation = 0;
		zha = new int[40][50];
		zha[10][10] = 1;		zha[10][11] = 1;		zha[10][12] = 1;
		setShape(zha);
	}
	
	public void setDeng()
	{
		generation = 0;
		deng = new int[40][50];
		deng[10][10] = 1;		deng[10][11] = 1;		deng[11][10] = 1;
		deng[12][13] = 1;		deng[13][13] = 1;		deng[13][12] = 1;
		setShape(deng);
	}
	
	public void setYi()
	{
		generation = 0;
		yi = new int[40][50];
		yi[21][11] = 1;		yi[21][12] = 1;		yi[21][13] = 1;
		yi[21][14] = 1;		yi[21][15] = 1;		yi[21][16] = 1;
		yi[21][17] = 1;		yi[21][18] = 1;		yi[21][19] = 1;
		yi[21][24] = 1;		yi[21][25] = 1;		yi[21][26] = 1;
		yi[21][27] = 1;		yi[21][28] = 1;		yi[21][29] = 1;
		yi[21][30] = 1;		yi[21][31] = 1;		yi[21][32] = 1;
		yi[21][33] = 1;		yi[21][39] = 1;		yi[21][40] = 1;		yi[21][41] = 1;
		setShape(yi);
	}
	
	public void setEr()
	{
		generation = 0;
		er = new int[40][50];
		er[5][23] = 1;          er[6][23] = 1;         er[7][23] = 1;          er[8][23] = 1;          er[9][23] = 1;
		er[10][23] = 1;         er[11][23] = 1;        er[12][23] = 1;         er[13][23] = 1;         er[14][23] = 1;
		er[15][23] = 1;         er[16][23] = 1;        er[17][23] = 1;         er[18][23] = 1;         er[19][23] = 1;
		er[20][23] = 1;         er[21][23] = 1;        er[22][23] = 1;         er[23][23] = 1;         er[24][23] = 1;
		er[25][23] = 1;         er[26][23] = 1;        er[27][23] = 1;         er[28][23] = 1;         er[29][23] = 1;
		er[30][23] = 1;         er[31][23] = 1;        er[32][23] = 1;         er[33][23] = 1;         er[34][23] = 1;
		er[5][25] = 1;          er[6][25] = 1;         er[7][25] = 1;          er[8][25] = 1;          er[9][25] = 1;
		er[10][25] = 1;         er[11][25] = 1;        er[12][25] = 1;         er[13][25] = 1;         er[14][25] = 1;
		er[15][25] = 1;         er[16][25] = 1;        er[17][25] = 1;         er[18][25] = 1;         er[19][25] = 1;
		er[20][25] = 1;         er[21][25] = 1;        er[22][25] = 1;         er[23][25] = 1;         er[24][25] = 1;
		er[25][25] = 1;         er[26][25] = 1;        er[27][25] = 1;         er[28][25] = 1;         er[29][25] = 1;
		er[30][25] = 1;         er[31][25] = 1;        er[32][25] = 1;         er[33][25] = 1;         er[34][25] = 1;
		setShape(er);
	}
	
	public void setWan()
	{
		generation = 0;
		wan = new int[40][50];
		wan[10][10] = 1;		wan[10][11] = 1;		wan[11][11] = 1;
		wan[9][16] = 1;		    wan[11][15] = 1;		wan[11][16] = 1;		wan[11][17] = 1;
		setShape(wan);
	}
	
	public void setShui()
	{
		generation = 0;
		shui = new int[40][50];
		shui[10][14] = 1;		shui[10][15] = 1;		shui[10][16] = 1;        shui[11][15] = 1;
		shui[12][14] = 1;		shui[12][15] = 1;		shui[12][16] = 1;        shui[12][17] = 1;              shui[13][18] = 1;
		setShape(shui);
	}
	
	public void setHua()
	{
		generation = 0;
		hua = new int[40][50];
		hua[10][10] = 1;		hua[10][11] = 1;		hua[10][12] = 1;
		hua[9][12] = 1;		hua[8][11] = 1;
		setShape(hua);
	}
	
	public void setHua_fa()
	{
		generation = 0;
		hua_fa = new int[40][50];
		hua_fa[10][10] = 1;			hua_fa[10][11] = 1;			hua_fa[11][11] = 1;			hua_fa[11][10] = 1;			
		hua_fa[10][20] = 1;			hua_fa[9][21] = 1;		 	hua_fa[8][22] = 1;			hua_fa[8][23] = 1;	
		hua_fa[9][25] = 1;			hua_fa[11][20] = 1;			hua_fa[10][26] = 1;			hua_fa[11][24] = 1;		
		hua_fa[11][26] = 1;			hua_fa[11][27] = 1;			hua_fa[12][20] = 1;			hua_fa[13][21] = 1;	
		hua_fa[14][22] = 1;			hua_fa[14][23] = 1;	 		hua_fa[13][25] = 1;			hua_fa[12][26] = 1;	
		hua_fa[10][30] = 1;			hua_fa[10][31] = 1;			hua_fa[9][30] = 1;			hua_fa[9][31] = 1;	
		hua_fa[8][30] = 1;			hua_fa[8][31] = 1;			hua_fa[7][32] = 1;		 	hua_fa[7][34] = 1;	
		hua_fa[6][34] = 1;			hua_fa[11][32] = 1;			hua_fa[11][34] = 1;			hua_fa[12][34] = 1;	
		hua_fa[9][44] = 1;			hua_fa[9][45] = 1;			hua_fa[8][44] = 1;			hua_fa[8][45] = 1;	
		setShape(hua_fa);
	}
	
	public void setRand()
	{
		generation = 0;
		rand = new int[40][50];
		int num = rows * columns;
		int index1 =(int)(Math.random() * 500) + 600;

		for (int i = 0; i < index1; i++) {
			int index2 = (int)(Math.random()*2000) % num;
			rand[index2 % rows][index2 / rows] = 1;
		}
		setShape(rand);
	}
	
	public void setSpeed(int speed1)
	{
		speed = speed1;
	}
	
	private void setShape(int[][] shape)
	{
		isChanging = true;
		
		int arrowsRows = shape.length;
		int arrowsColumns = shape[0].length;
		
		int minimumRows = (arrowsRows < rows) ? arrowsRows: rows;
		int minimumColumns = (arrowsColumns < columns) ? arrowsColumns : columns;
		
		synchronized(this)
		{
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					currentGeneration[i][j] = CellStatus.Dead;
				}
			}
			
			for(int i = 0; i < minimumRows; i++)
			{
				for(int j = 0; j < minimumColumns; j++)
				{
					if(shape[i][j] == 1)
					{
						currentGeneration[i][j] = CellStatus.Active;
					}
				}
			}
			
			isChanging = false;
			this.notifyAll();
		}		
	}
	
	private void evolve(int x, int y)
	{
		int activeSurroundingCell = 0;
		
		if(isValidCell(x - 1, y - 1) && (currentGeneration[x - 1][y - 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x, y - 1) && (currentGeneration[x][y - 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}	
		
		if(isValidCell(x + 1, y - 1) && (currentGeneration[x + 1][y - 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x + 1, y) && (currentGeneration[x + 1][y] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}
		
		if(isValidCell(x + 1, y + 1) && (currentGeneration[x + 1][y + 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x, y + 1) && (currentGeneration[x][y + 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}
		
		if(isValidCell(x - 1, y + 1) && (currentGeneration[x - 1][y + 1] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}

		if(isValidCell(x - 1, y) && (currentGeneration[x - 1][y] == CellStatus.Active))
		{
			activeSurroundingCell++;
		}	
		
		if(activeSurroundingCell == 3)
		{
			nextGeneration[x][y] = CellStatus.Active;
		}
		else if(activeSurroundingCell == 2)
		{
			nextGeneration[x][y] = currentGeneration[x][y];
		}
		else
		{
			nextGeneration[x][y] = CellStatus.Dead;
		}
	}
	
	private boolean isValidCell(int x, int y)
	{
		if((x >= 0) && (x < rows) && (y >= 0) && (y < columns))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void sleep(int x)
	{
		try 
		{
			Thread.sleep(speed * x);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	static enum CellStatus
	{
		Active,
		Dead;
	}
		
	
	private static int[][] arrow;		
	private static int[][] square;
	private static int[][] zhuan;
	private static int[][] feng;
	private static int[][] zha;
	private static int[][] deng;
	private static int[][] yi;
	private static int[][] er;
	private static int[][] wan;
	private static int[][] shui;
	private static int[][] hua;
	private static int[][] hua_fa;
	private static int[][] rand;
	private int speed;
}
