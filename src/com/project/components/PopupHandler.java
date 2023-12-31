package com.project.components;

import com.project.commons.Memory;

import javax.swing.*;
import java.awt.*;


/**
 * logic for the popup that is being shown ;when we load memory / pressed the Init Button
 */
public class PopupHandler {
    private final JFrame parentFrame;
    private JDialog progressDialog;
    private JProgressBar progressBar;
    private boolean isTaskRunning;

    public PopupHandler(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public void showPopup(String ActionString, String successString, String failureString, Boolean condition) {
        progressDialog = new JDialog(parentFrame, ActionString, true);
        progressDialog.setSize(300, 100);
        progressDialog.setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Start the progress bar when the dialog is created
        startProgressBar(successString, failureString, condition);

        progressDialog.add(progressBar, BorderLayout.CENTER);


        progressDialog.setLocationRelativeTo(parentFrame); // Center the dialog relative to the parent frame
        progressDialog.setVisible(true);
    }

    private void startProgressBar(String successString, String failureString, Boolean condition) {
        if (!isTaskRunning) {
            isTaskRunning = true;

            SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    for (int i = 0; i <= 50; i++) {
                        Thread.sleep(20); // Simulate work
                        publish(i);
                    }

                    return true;
                }

                @Override
                protected void process(java.util.List<Integer> chunks) {
                    int progressValue = chunks.get(chunks.size() - 1);
                    progressBar.setValue(progressValue);
                }

                @Override
                protected void done() {
                    try {
                        if (condition) {
                            progressBar.setValue(100);
                            progressDialog.dispose();
                            JOptionPane.showMessageDialog(parentFrame, successString, "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            progressDialog.dispose();
                            JOptionPane.showMessageDialog(parentFrame, failureString,
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
