package com.project.components;

import com.project.commons.Memory;

import javax.swing.*;
import java.awt.*;


public class PopupHandler {
    private final JFrame parentFrame;
    private JDialog progressDialog;
    private JProgressBar progressBar;
    private boolean isTaskRunning;

    public PopupHandler(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void showPopup() {
        progressDialog = new JDialog(parentFrame, "IPL file Loading", true);
        progressDialog.setSize(300, 100);
        progressDialog.setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Start the progress bar when the dialog is created
        startProgressBar();

        progressDialog.add(progressBar, BorderLayout.CENTER);


        progressDialog.setLocationRelativeTo(parentFrame); // Center the dialog relative to the parent frame
        progressDialog.setVisible(true);
    }

    private void startProgressBar() {
        if (!isTaskRunning) {
            isTaskRunning = true;

            SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    for (int i = 0; i <= 50; i++) {
                        Thread.sleep(20); // Simulate work
                        publish(i);
                    }

                    return Memory.loadMemory();
                }

                @Override
                protected void process(java.util.List<Integer> chunks) {
                    int progressValue = chunks.get(chunks.size() - 1);
                    progressBar.setValue(progressValue);
                }

                @Override
                protected void done() {
                    try {
                        boolean condition = get();

                        if (condition) {
                            progressBar.setValue(100);
                            progressDialog.dispose();
                            JOptionPane.showMessageDialog(parentFrame, "Task completed successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            progressDialog.dispose();
                            JOptionPane.showMessageDialog(parentFrame, "Error occurred during the task!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        isTaskRunning = false;
                    }
                }
            };

            worker.execute();
        }
    }


}
