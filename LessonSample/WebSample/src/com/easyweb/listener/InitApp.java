package com.easyweb.listener;

import java.beans.PropertyEditorManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.easyweb.bean.editor.DateEditor;
import com.easyweb.bean.editor.SqlDateEditor;
import com.easyweb.bean.editor.SqlTimeEditor;
import com.easyweb.bean.editor.SqlTimestampEditor;

@WebListener
public class InitApp implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		registerPropertyEditor();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
	/**
	 * 用来注册属性编辑器。
	 */
	private void registerPropertyEditor() {
		PropertyEditorManager.registerEditor(java.util.Date.class, DateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Date.class, SqlDateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Time.class, SqlTimeEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Timestamp.class, SqlTimestampEditor.class);
	}
}
