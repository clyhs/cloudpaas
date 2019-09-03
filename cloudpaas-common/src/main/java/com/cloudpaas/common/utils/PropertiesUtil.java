package com.cloudpaas.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chenly
 *
 * @version createtime:2016-7-28 下午2:06:55
 */
@SuppressWarnings("all")
public class PropertiesUtil {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private LinkedHashMap fields;// 该文件字段
	private PropertiesZUtils properties = new PropertiesZUtils();
	private InputStream is = null;
	private File file = null;
	private Boolean isAllReaded = Boolean.FALSE;// 是否已经读取读取全部
	private FileOutputStream fos = null;

	public PropertiesUtil() {
		this.properties = new PropertiesZUtils();
	}

	public PropertiesUtil(PropertiesZUtils properties) {
		this.properties = properties;
	}

	/***
	 * 
	 * @Title: 加载文件load(String fileName) @Description: PropertiesUtil pu = new
	 *         PropertiesUtil() pu. load("D:/test.properties") ; @param @param
	 *         fileName 配置文件的路径 @return Properties 返回类型 @throws
	 */
	public Properties load(String fileName) {
		file = new File(fileName);
		if (!file.exists()) {
			logger.error("file path");
		}
		if (file.isDirectory()) {
			logger.error("file  is Directory");
		}
		return load(this.file);
	}

	/***
	 * 
	 * @Title: load @Description: PropertiesUtil pu = new PropertiesUtil() pu.
	 *         load(new File("D:/test.properties")) ; @param @param file @return
	 *         Properties 返回类型 @throws
	 */
	public Properties load(File file) {
		if (!(file.exists() && file.isFile())) {
			logger.error(" file  is not exists");
		}
		FileInputStream fip = null;
		try {
			fip = new FileInputStream(file);

			this.is = new BufferedInputStream(fip);
			return load(this.is);
		} catch (FileNotFoundException e) {
			logger.error("load error");
			if (fip != null) {
				try {
					fip.close();
				} catch (IOException e1) {
					logger.error("load error");
				}
			}
		}
		return null;
	}

	/***
	 * 
	 * @Title: load @Description: PropertiesUtil pu = new PropertiesUtil() pu.
	 *         load(new FileInputStream(new File("D:/test.properties")))
	 *         ; @param @param is @param @return 设定文件 @return Properties
	 *         返回类型 @throws
	 */
	public Properties load(InputStream is) {
		try {
			this.properties.load(is);
			if (this.fields == null) {
				this.fields = new LinkedHashMap();
			} else {
				this.fields.clear();
			}
		} catch (IOException e) {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ee) {
					logger.error("load error");
				}
			}
			logger.error("load error");
		}
		return properties;
	}

	/***
	 * 根据类路径加载配置文件
	 * 
	 * @Title: loadAsClass @Description: PropertiesUtil pu = new
	 *         PropertiesUtil() pu. load(test.properties,Test.getClass())
	 *         ; @param @param file @param @param clazz @param @return
	 *         设定文件 @return Properties 返回类型 @throws
	 */
	public Properties loadAsClass(String file, Class clazz) {
		return load(clazz.getResourceAsStream(file));
	}

	/****
	 * 
	 * @Title: getAllProperties @Description: 获取所有配置文件 @param @return
	 *         设定文件 @return Map <key,value> 返回类型 @throws
	 */
	public Map getAllProperties() {
		if (this.properties == null) {
			logger.error("properties is null!Please  loading files first!");
			return null;
		}
		if (this.fields == null) {
			this.fields = new LinkedHashMap();

		}
		Set set = this.properties.keySet();
		for (Object key : set) {

			this.fields.put((String) key, this.properties.getProperty((String) key));
		}
		if (logger.isDebugEnabled()) {

			logger.debug("load properties");
		}
		this.isAllReaded = Boolean.TRUE;
		return this.fields;
	}

	/****
	 * 
	 * @Title: getValueByKey @Description: read value by key ,if you knew zhe
	 *         key! @param @param key @param @return 设定文件 @return String
	 *         返回类型 @throws
	 */
	public String getValueByKey(String key) {
		if (this.properties == null) {
			logger.error("properties is null!Please loading files first!");
		}
		if (this.fields == null || this.fields.size() == 0) {
			getAllProperties();
		}
		return (String) (this.fields.get(key) == null ? this.properties.getProperty(key) : this.fields.get(key));
	}

	/***
	 * 
	 * @Title: setValueByKey @Description: 保存一条配置信息 @param @param Key
	 *         需要保存的KEY @param @param value 需要保存的Vlue @param @param note 设定文件
	 *         需要对这条KEY 添加什么样的注释 @return void 返回类型 @throws
	 */
	public void setValueByKey(String Key, String value, String note) {
		if (this.file == null) {
			logger.error("file is null!Please  loading files first!");
			return;
		}
		if (this.properties == null) {
			logger.error("properties is null!Please  loading files first!");
			return;
		}
		if (Key == null) {
			logger.error("");
			return;
		}
		this.fields.put(Key, value);
		try {
			fos = new FileOutputStream(this.file);
			this.properties.setProperty(Key, value);
			this.properties.store(fos, null);
			fos.flush();
		} catch (FileNotFoundException e) {
			logger.error("set value error");
		} catch (IOException e) {
			logger.error("set value error");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("set value error");
				}
			}
		}
	}

	/***
	 * 
	 * @Title: list @Description:把列表输出到流里面 @param @param out 设定文件 @return void
	 *         返回类型 @throws
	 */
	public void list(PrintStream out) {
		if (this.properties == null) {
			logger.error("properties is null!Please  loading files first!");
			return;
		}
		this.properties.list(out);
	}

	public void removeByKey(String Keyname) {
		if (this.file == null) {
			logger.error("file is null!Please  loading files first!");
			return;
		}
		if (this.properties == null) {
			logger.error("properties is null!Please  loading files first!");
			return;
		}
		if (Keyname == null) {
			logger.error("");
			return;
		}
		// this.fields.remove(Key);
		try {
			fos = new FileOutputStream(this.file);

			this.properties.remove(Keyname);

			this.properties.store(fos, "Update '" + Keyname + "' value");
			fos.flush();
		} catch (FileNotFoundException e) {
			logger.error("set value error");
		} catch (IOException e) {
			logger.error("set value error");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error("set value error");
				}
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.fields = null;
		super.finalize();
	}

	public void close() throws IOException {
		if (this.is != null) {
			is.close();
		}
		if (this.fos != null) {
			fos.flush();
			fos.close();
		}
	}

	/***
	 * 
	 * @Title: getByFuzzyKey @Description: 模糊查找 @param @param
	 *         key @param @return @return Map 返回类型 @throws
	 */
	public Map getByFuzzyKey(String key) {
		if (!this.isAllReaded) {
			this.getAllProperties();
		}
		Map map = new HashMap();
		Set keys = this.fields.keySet();
		for (Object kk : keys) {
			if (((String) kk).indexOf(key) >= 0) {
				map.put(kk, this.fields.get(keys));
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String filepath = "D:/eclipse64/devops/pascloud-devops-parent/pascloud-webapps/src/main/webapp/static/resources/service/conf/config.properties";
		                   //D:/eclipse64/devops/pascloud-devops-parent/pascloud-webapps/src/main/webapp/static/resources/service/conf/
		PropertiesUtil p = new PropertiesUtil();
		p.load(filepath);

		// System.out.println(p.getValueByKey("pascloud.dev"));
		// p.setValueByKey("pascloud.dev", "true", "");

		Map map = p.getByFuzzyKey("dn");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> obj = it.next();
			System.out.println(obj.getKey() + p.getValueByKey(obj.getKey()));
		}

		// p.setValueByKey("dn17.type", "ora", "");

		// p.removeByKey("dn17.type");

	}

}