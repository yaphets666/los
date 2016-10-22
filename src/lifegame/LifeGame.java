package lifegame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class LifeGame extends JFrame{
	private final World world;
	
	public LifeGame(int rows, int columns)
	{
		world = new World(rows, columns);
		new Thread(world).start();
		add(world);		
	}
	
	public static void main(String[] args) 
    {
    	LifeGame frame = new LifeGame(40, 50);
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);      
        
        JMenu options = new JMenu("Style");
        menu.add(options);
        
        JMenuItem arrow = options.add("Arrow");
        arrow.addActionListener(frame.new ArrowActionListener());
        
        JMenuItem square = options.add("Square");
        square.addActionListener(frame.new SquareActionListener());        
        
        JMenuItem zhuan = options.add("砖块");
        zhuan.addActionListener(frame.new ZhuanActionListener());
        
        JMenuItem feng = options.add("蜂窝");
        feng.addActionListener(frame.new FengActionListener());
        
        JMenuItem zha = options.add("眨眼者");
        zha.addActionListener(frame.new ZhaActionListener());
        
        JMenuItem deng = options.add("灯塔");
        deng.addActionListener(frame.new DengActionListener());
        
        JMenuItem yi = options.add("一字型");
        yi.addActionListener(frame.new YiActionListener());
        
        JMenuItem er = options.add("两竖列");
        er.addActionListener(frame.new ErActionListener());
        
        JMenuItem wan = options.add("顽固者");
        wan.addActionListener(frame.new WanActionListener());
        
        JMenuItem shui = options.add("水龙头");
        shui.addActionListener(frame.new ShuiActionListener());
        
        JMenuItem hua = options.add("滑翔机");
        hua.addActionListener(frame.new HuaActionListener());
        
        JMenuItem hua_fa = options.add("滑翔机发射器");
        hua_fa.addActionListener(frame.new Hua_faActionListener());       
        
        JMenuItem rand = options.add("随机");
        rand.addActionListener(frame.new RandActionListener());
        
        JMenu speed = new JMenu("Speed");        
        menu.add(speed);
        
        JMenuItem low = speed.add("Low");
        low.addActionListener(frame.new LowActionListener());
        
        JMenuItem mid = speed.add("Mid");
        mid.addActionListener(frame.new MidActionListener());
        
        JMenuItem high = speed.add("High");
        high.addActionListener(frame.new HighActionListener());
        
        JMenu help = new JMenu("Help");        
        menu.add(help);
        
        JMenuItem hel = help.add("帮助");
        hel.addActionListener(frame.new HelpActionListener());              
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1007, 859);
        
        int windowWidth = frame.getWidth(); //获得窗口宽
        int windowHeight = frame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示               
	    
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.setResizable(false);
    }
	
	class ArrowActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		world.setArrow();
    	}
    }
    
    class SquareActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		world.setSquare();
    	}
    } 
    
    class ZhuanActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setZhuan();
    	}
    }
    
    class FengActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setFeng();
    	}
    }
    
    class ZhaActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setZha();
    	}
    }
    
    class DengActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setDeng();
    	}
    }
    
    class YiActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setYi();
    	}
    }
    
    class ErActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setEr();
    	}
    }
    
    class WanActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setWan();
    	}
    }
    
    class ShuiActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setShui();
    	}
    }
    
    class HuaActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setHua();
    	}
    }
    
    class Hua_faActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setHua_fa();
    	}
    }
    
    class RandActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setRand();
    	}
    }
    
    class HelpActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		JFrame frame = new JFrame();
 //   		JOptionPane.showMessageDialog(frame,"1.control——控制游戏的进行\n\n2.style——选择样式\n\n3.speed——挑选变化速度\n\n4.help——显示帮助\n");
    		JOptionPane.showMessageDialog(frame,"1.style  ——  选择样式\n\n2.speed  ——  挑选变化速度\n\n3.help  ——  显示帮助\n");
    	}
    }
    
    class LowActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setSpeed(1000);
    	}
    }
    
    class MidActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setSpeed(500);
    	}
    }
    
    class HighActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		world.setSpeed(100);
    	}
    }
}
