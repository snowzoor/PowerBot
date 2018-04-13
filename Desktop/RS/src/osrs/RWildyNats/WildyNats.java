package osrs.RWildyNats;


import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.GeItem;
import osrs.RWildyNats.WildyNatsTasks.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Script.Manifest(
        name ="WildyNats",
        description = "Telegrabs Nature Runes",
        properties = "author=snowzoor; topic=1337; client=4;")
public class WildyNats extends PollingScript<ClientContext> implements PaintListener {
    List<Task> taskList = new ArrayList<Task>();
    private final Color color2 = new Color(158, 50, 50);
    private final Color color1 = new Color(204, 187, 154, 255);
    private final Font font1 = new Font("Arial", 2, 22);


    private int startexp;
    public static int resizable = 0;
    private int wineprice;
    public static double factorresizable = 0.9;
    public static boolean pked=false;
    public static int Nature_Rune_ID = 561;
    public static int LAWRUNE_ID = 563;
    public static int FIRERUNE_ID = 554;
    private final BasicStroke stroke1 = new BasicStroke(1);

    public static Point UNINITIALIZED_POINT = new Point(-1, -1); // destination of our mouse if we have not clicked
    public static Point lastItemPoint = UNINITIALIZED_POINT;


    public static String CurrentTask = "";
    public static int died = 0;
    public static int wines = 0;
    private BufferedImage downloadBackground(String url) {
        if(url.isEmpty())   url ="http://i.imgur.com/O98SPhr.png";
        try {
            String name = "wildywinessss.png";
            if (!new File(getStorageDirectory() + "\\" +name).exists())
                download(url, name);
            File file = new File(getStorageDirectory() + "\\" + name);
            return ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Image img = downloadBackground("http://i.imgur.com/AIiiKd8.png");


    public void start() {
        ctx.properties.put("randomevents.disable",true);
        if(img == null) {
            img = downloadBackground("http://i.imgur.com/AIiiKd8.png");
        }

            ctx.input.speed(70);
        taskList.add(new WalkingToNats(ctx));
        taskList.add(new AntiPKAntiBan(ctx));
        taskList.add(new EquippingAndTP(ctx));
        taskList.add(new Banking(ctx));
        taskList.add(new BankingLumby(ctx));

        taskList.add(new WalkingToBank(ctx));
        taskList.add(new TPtoVar(ctx));
        taskList.add(new WalkingToLumbyBank(ctx));
        taskList.add(new WalkingToWild20(ctx));
        taskList.add(new PreparingToGrab(ctx));
        taskList.add(new GrabbingNats(ctx));

        taskList.add(new WalkingToDitch(ctx));


        wineprice = new GeItem(Nature_Rune_ID).price;


        startexp = ctx.skills.experience(Constants.SKILLS_MAGIC);

    }

    @Override
    public void poll() {
        for (Task t : taskList) {


            if (t.activate()) {


                t.execute();
                System.out.println(CurrentTask);
                break;
            }
        }


    }

    @Override
    public void repaint(Graphics g1) {
        int expGained = ctx.skills.experience(Constants.SKILLS_MAGIC)-startexp;
        long milliseconds = this.getTotalRuntime();
        long seconds = (milliseconds / 1000) % 60;
        long minutes = ((milliseconds / 1000) / 60) % 60;
        long hours = ((milliseconds / 1000) / 3600) % 24;
        Graphics2D g = (Graphics2D)g1;
        if(ctx.game.resizable()) {
            resizable=72;
            factorresizable= 0.7;
        } else {
            factorresizable=0.9;
            resizable=0;
        }
        g.setFont(font1);

        if(img != null) {
            g.drawImage(img,    -1, (int)(resizable+328+(ctx.game.dimensions().getHeight()-575)), null);
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        g.setColor(color1);
        g.fillRect(9, (int)(resizable+462+(ctx.game.dimensions().getHeight()-575)), 146, 11);
        g.setColor(color2);
        g.drawString("        " + String.format("%02d:%02d:%02d", hours, minutes, seconds), 90,  (int)(resizable+398+(ctx.game.dimensions().getHeight()-575)));

        g.drawString("      " + CurrentTask, 261,  (int)(resizable+353+(ctx.game.dimensions().getHeight()-575)));

        g.drawString("             " +wines+ " (" + (int) (wines *(3600000D/milliseconds))+"/h" + ")", 300,  (int)(resizable+398+(ctx.game.dimensions().getHeight()-575)));
        g.drawString("             " + formatter.format(expGained *(3600000D/milliseconds)), 300,  (int)(resizable+439+(ctx.game.dimensions().getHeight()-575)));
        g.drawString("          " +formatter.format(wines*wineprice)+ " (" +formatter.format(wines * wineprice * (3600000D / milliseconds)) + " gp/h" + ")", 65,  (int)(resizable+439+(ctx.game.dimensions().getHeight()-575)));
        g.drawString("      " + died, 430,  (int)(resizable+398+(ctx.game.dimensions().getHeight()-575)));

    }
}
