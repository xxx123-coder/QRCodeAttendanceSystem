package com.qrcode.attendance.main;

import com.qrcode.attendance.config.AppConfig;
import com.qrcode.attendance.gui.frame.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * 应用程序启动类
 * 包含main方法，程序入口点
 */
public class ApplicationLauncher {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLauncher.class);

    public static void main(String[] args) {
        logger.info("正在启动二维码签到系统...");

        // 设置Swing外观
        setLookAndFeel();

        // 初始化应用程序
        SwingUtilities.invokeLater(() -> {
            try {
                // 加载配置
                AppConfig.loadConfig();

                // 创建并显示主窗口
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);

                logger.info("应用程序启动成功");

                // 初始化数据目录
                DataInitializer.initDataDirectories();

            } catch (Exception e) {
                logger.error("应用程序启动失败", e);
                JOptionPane.showMessageDialog(null,
                        "应用程序启动失败: " + e.getMessage(),
                        "错误",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }

    /**
     * 设置Swing外观和感觉
     */
    private static void setLookAndFeel() {
        try {
            // 使用系统默认外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // 设置一些全局UI属性
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            UIManager.put("Button.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("Label.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("TextField.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("Table.font", new Font("Microsoft YaHei", Font.PLAIN, 12));

        } catch (Exception e) {
            logger.warn("无法设置系统外观，使用默认外观", e);
        }
    }
}