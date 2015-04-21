package ru.ifmo.lang.Sadovnikov.t08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by alexkane on 4/21/15.
 */
public class View extends JFrame {
    private final ActionListener buttons;
    private JButton normalGun;
    private JButton bonusGun;

    public View(FileBasedRussianRoulette model, Controller controller) {
        buttons = new NormalListener(controller, model, this);
    }

    public void createHomeScreen() {
        this.setTitle("Русская рулетка");
        this.setVisible(true);
        this.setSize(375, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        normalGun = new JButton("Обычная игра");
        homePanel.add(normalGun);
        normalGun.addActionListener(buttons);
        homePanel.add(Box.createHorizontalStrut(15));
        bonusGun = new JButton("Бонусный пистолет");
        homePanel.add(bonusGun);
        bonusGun.addActionListener(buttons);
        homePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(homePanel);
    }

    class NormalListener implements ActionListener, Observer {
        private final Controller controller;
        private final View view;
        private String parameter;
        private JButton bullet1;
        private JButton bullet2;
        private JButton bullet3;
        private JButton bullet4;
        private JButton bullet5;
        private JButton bullet6;
        private JLabel bullets;
        private JFileChooser sourceDirectory;
        private JButton playButton;
        private int numberOfBullets;
        private String sourceDirectoryString = "";
        private JLabel messageBoard;
        private JLabel messageBoardMain;

        public NormalListener(Controller controller, FileBasedRussianRoulette model, View view) {
            this.controller = controller;
            this.view = view;
            if (model.getNumberOfObservers() == 0) {
                model.registerObserver(this);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.normalGun) {
                parameter = "normal";
            }
            if (e.getSource() == view.bonusGun) {
                parameter = "bonus";
            }
            if (e.getSource() == bullet1) {
                numberOfBullets = 1;
                bullets.setText("1");
            } else if (e.getSource() == bullet2) {
                numberOfBullets = 2;
                bullets.setText("2");
            } else if (e.getSource() == bullet3) {
                numberOfBullets = 3;
                bullets.setText("3");
            } else if (e.getSource() == bullet4) {
                numberOfBullets = 4;
                bullets.setText("4");
            } else if (e.getSource() == bullet5) {
                numberOfBullets = 5;
                bullets.setText("5");
            } else if (e.getSource() == bullet6) {
                numberOfBullets = 6;
                bullets.setText("6");
            } else if (e.getSource() == playButton) {
                if (sourceDirectory.getSelectedFile() == null) {
                    JOptionPane.showMessageDialog(sourceDirectory, "Вы не выбрали директорию!", "Директория не выбрана", JOptionPane.WARNING_MESSAGE);
                } else {
                    sourceDirectoryString = sourceDirectory.getSelectedFile().getAbsolutePath();
                    if (parameter.equals("normal")) {
                        try {
                            controller.playNormalGun(numberOfBullets, sourceDirectoryString);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            controller.playBonusGun(sourceDirectoryString);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } else {
                createGame(parameter);
            }
        }

        public void createGame(final String parameter) {
            JFrame normalGame = new JFrame("Обычная игра");
            normalGame.setVisible(true);
            normalGame.setSize(1000, 600);

            normalGame.getContentPane().setLayout(new BoxLayout(normalGame.getContentPane(), BoxLayout.Y_AXIS));

            if (parameter.equals("bonus")) {
                JPanel topNormalGamePanel = new JPanel();
                topNormalGamePanel.add(new JLabel("Время в Индии:  "));
                topNormalGamePanel.setLayout(new BoxLayout(topNormalGamePanel, BoxLayout.X_AXIS));

                final Calendar indian = new GregorianCalendar();
                indian.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
                topNormalGamePanel.add(new JLabel(indian.get(Calendar.HOUR_OF_DAY) + ":" + indian.get(Calendar.MINUTE) + ":" + indian.get(Calendar.SECOND)));
                topNormalGamePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                normalGame.getContentPane().add(topNormalGamePanel);
            } else {
                JPanel topNormalGamePanel = new JPanel();
                topNormalGamePanel.add(new JLabel("Выберите чило пуль:  "));
                topNormalGamePanel.setLayout(new BoxLayout(topNormalGamePanel, BoxLayout.X_AXIS));

                bullet1 = new JButton("Одна пуля");
                topNormalGamePanel.add(bullet1);
                bullet1.addActionListener(this);

                bullet2 = new JButton("Две пули");
                topNormalGamePanel.add(bullet2);
                bullet2.addActionListener(this);

                bullet3 = new JButton("Три пули");
                topNormalGamePanel.add(bullet3);
                bullet3.addActionListener(this);

                bullet4 = new JButton("Четыре пули");
                topNormalGamePanel.add(bullet4);
                bullet4.addActionListener(this);

                bullet5 = new JButton("Пять пуль");
                topNormalGamePanel.add(bullet5);
                bullet5.addActionListener(this);

                bullet6 = new JButton("Полный барбан");
                topNormalGamePanel.add(bullet6);
                bullet6.addActionListener(this);
                topNormalGamePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                normalGame.getContentPane().add(Box.createVerticalStrut(5));

                JPanel bulletsPanel = new JPanel();
                bulletsPanel.setLayout(new BoxLayout(bulletsPanel, BoxLayout.X_AXIS));
                bulletsPanel.add(new JLabel("Число пуль в барабане:  "));
                bullets = new JLabel("0");
                bulletsPanel.add(bullets);
                bulletsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                normalGame.getContentPane().add(bulletsPanel);

                normalGame.getContentPane().add(topNormalGamePanel);
            }

            normalGame.getContentPane().add(Box.createVerticalStrut(15));

            JPanel bottomNormalGamePanel = new JPanel();
            bottomNormalGamePanel.setLayout(new BoxLayout(bottomNormalGamePanel, BoxLayout.X_AXIS));
            normalGame.add(bottomNormalGamePanel);

            sourceDirectory = new JFileChooser("Выбрать директорию");
            sourceDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            sourceDirectory.setDialogType(JFileChooser.CUSTOM_DIALOG);
            sourceDirectory.setControlButtonsAreShown(false);
            sourceDirectory.addActionListener(this);
            bottomNormalGamePanel.add(sourceDirectory);
            bottomNormalGamePanel.add(Box.createHorizontalStrut(30));

            playButton = new JButton("Играть!");
            playButton.addActionListener(this);
            bottomNormalGamePanel.add(playButton);
            bottomNormalGamePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            normalGame.getContentPane().add(bottomNormalGamePanel);

            normalGame.getContentPane().add(Box.createVerticalStrut(15));

            JPanel finalPanel = new JPanel();
            finalPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            messageBoardMain = new JLabel("Результат игры:");
            finalPanel.add(messageBoardMain);
            messageBoard = new JLabel("");
            finalPanel.add(messageBoard);
            finalPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            normalGame.getContentPane().add(finalPanel);
        }

        @Override
        public void update(String message) {
            messageBoard.setText(message);
        }
    }
}
