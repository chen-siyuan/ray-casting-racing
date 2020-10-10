import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Frame extends JFrame implements Runnable {

    private static final Map<String, Integer> codeToIndex = new HashMap<>();
    private static final Map<Integer, Integer> keyToIndex = new HashMap<>();
    private static final Set<Integer> singlePressKeys = new HashSet<>(Arrays.asList(
            KeyEvent.VK_BACK_SLASH, KeyEvent.VK_SPACE
    ));

    private final VisionPanel visionPanel;
    private final MapPanel mapPanel;
    private final ControlPanel controlPanel;
    private final Set<Edge> map;
    private final Observer observer;
    private final Body body;

    private boolean[] status;

    // TODO: 10/3/20 take a look at drifting

    public Frame() throws IOException {
        this("counterstrike");
    }

    public Frame(String paletteName) throws IOException {

        PaletteParser palette = new PaletteParser(paletteName);
        palette.parsePalette();

        visionPanel = new VisionPanel(palette.getVision());
        mapPanel = new MapPanel(palette.getMap());
        controlPanel = new ControlPanel(palette.getControl());

        map = new HashSet<>();
        observer = new Observer(Main.OBSERVER_RAYS, Main.OBSERVER_SPAN);
        body = new Body();

        initStatus();

    }

    public Set<Edge> getMap() {
        return map;
    }

    public Observer getObserver() {
        return observer;
    }

    private void initStatus() {

        int n = 16;
        status = new boolean[n];

        String[] codes = new String[]{
                "forward", "backward", "counterclockwise", "clockwise",
                "boostForward", "boostBackward", "boostCounterclockwise", "boostClockwise",
                "zoomOut", "zoomIn", "mode", "reset",
                "focusOut", "focusIn", "mapOut", "mapIn"
        };
        int[] keys = new int[]{
                KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
                KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_MINUS, KeyEvent.VK_EQUALS, KeyEvent.VK_BACK_SLASH, KeyEvent.VK_SPACE,
                KeyEvent.VK_OPEN_BRACKET, KeyEvent.VK_CLOSE_BRACKET, KeyEvent.VK_9, KeyEvent.VK_0
        };

        IntStream.range(0, n).forEach(i -> {
            codeToIndex.put(codes[i], i);
            keyToIndex.put(keys[i], i);
        });

    }

    public void initUI() {

        setTitle("ray-tracing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(visionPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(mapPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(controlPanel, gbc);

        pack();
        setLocationRelativeTo(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Integer index;
                if((index = keyToIndex.get(e.getExtendedKeyCode())) != null) status[index] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Integer index;
                if((index = keyToIndex.get(e.getExtendedKeyCode())) != null
                        && !singlePressKeys.contains(index)) status[index] = false;
            }
        });

    }

    @Override
    public void addNotify() {
        super.addNotify();
        new Thread(this).start();
    }

    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()) {

            // VisionPanel

            AtomicInteger gear = new AtomicInteger();
            if(status[codeToIndex.get("forward")])
                gear.addAndGet(status[codeToIndex.get("boostForward")] ? 5 : 2);
            if(status[codeToIndex.get("backward")])
                gear.addAndGet(status[codeToIndex.get("boostBackward")] ? -2 : -1);
            body.updateGear(gear.get());

            AtomicInteger turn = new AtomicInteger();
            if(status[codeToIndex.get("counterclockwise")])
                turn.addAndGet(status[codeToIndex.get("boostCounterclockwise")] ? -2 : -1);
            if(status[codeToIndex.get("clockwise")])
                turn.addAndGet(status[codeToIndex.get("boostClockwise")] ? 2 : 1);
            body.updateTurn(turn.get());

            if(status[codeToIndex.get("mapOut")] != status[codeToIndex.get("mapIn")])
                if(status[codeToIndex.get("mapOut")]) {
                    body.setScale(9. / 10.);
                    mapPanel.setZoom(10. / 9.);
                } else {
                    body.setScale(10. / 9.);
                    mapPanel.setZoom(9. / 10.);
                }

            body.update();

            observer.setPosition(body.getPosition());
            observer.setDirection(body.getVelocity().getDirection());

            body.reset();

            if(status[codeToIndex.get("focusOut")] != status[codeToIndex.get("focusIn")])
                visionPanel.setFocal(status[codeToIndex.get("focusOut")] ? 4. / 5. : 5. / 4.);

            visionPanel.setHeightsFromDistances(observer.detect(map));
            visionPanel.repaint();

            // MapPanel

            if(status[codeToIndex.get("zoomOut")] != status[codeToIndex.get("zoomIn")])
                mapPanel.setZoom(status[codeToIndex.get("zoomOut")] ? 9. / 10. : 10. / 9.);

            if(status[codeToIndex.get("reset")])
                mapPanel.setCamera();
            if(status[codeToIndex.get("mode")])
                mapPanel.toggleMode();
            status[codeToIndex.get("reset")] = false;
            status[codeToIndex.get("mode")] = false;

            mapPanel.setMap(map);
            mapPanel.setObserver(observer);
            mapPanel.repaint();


            try {
                Thread.sleep((int)Math.round(1000. / Main.FRAME_RATE));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
