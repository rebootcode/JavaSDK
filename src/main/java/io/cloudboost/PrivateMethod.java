package io.cloudboost;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import io.cloudboost.util.SqlLite;;

/**
 * 
 * @author cloudboost
 *
 */
class PrivateMethod{
	
	/**
	 * 
	 * _defaultColumns
	 * 
	 * @param type
	 * @return
	 */
	static ArrayList<JSONObject> _defaultColumns(String type){
		ArrayList<JSONObject> col = new ArrayList<JSONObject>();
		Column id = new Column("id", Column.DataType.Id, true, true);
		id.setIsRenamable(false);
		id.setIsDeletable(false);
		id.setIsEditable(false);
		
		Column expires = new Column("expires", Column.DataType.DateTime, false, false);
	    expires.setIsDeletable(false);
	    expires.setIsEditable(false);
	    
	    Column createdAt = new Column("createdAt", Column.DataType.DateTime, true, false);
	    createdAt.setIsDeletable(false);
	    createdAt.setIsEditable(false);
	    
	    Column updatedAt = new Column("updatedAt", Column.DataType.DateTime, true, false);
	    updatedAt.setIsDeletable(false);
	    updatedAt.setIsEditable(false);
	    
	    Column acl = new Column("ACL", Column.DataType.ACL, true, false);
	    acl.setIsDeletable(false);
	    acl.setIsEditable(false);
	    
	    col.add(id.document);
	    col.add(expires.document);
	    col.add(createdAt.document);
	    col.add(updatedAt.document);
	    col.add(acl.document);
	    
	    if(type == "custom"){
	    	return col;
	    }else if(type == "user"){
	    	Column username = new Column("username", Column.DataType.Text, true, true);
	        username.setIsDeletable(false);
	        username.setIsEditable(false);
	        
	        Column email = new Column("email", Column.DataType.Email, false, true);
	        email.setIsDeletable(false);
	        email.setIsEditable(false);
	        
	        Column password = new Column("password", Column.DataType.Password, true, false);
	        password.setIsDeletable(false);
	        password.setIsEditable(false);
	        
	        Column roles = new Column("roles", Column.DataType.List, false, false);
	        CloudTable role = new CloudTable("Role");
	        roles.setRelatedTo(role);
	        roles.setRelatedToType("role");
	        roles.setRelationType("table");
	        roles.setIsDeletable(false);
	        roles.setIsEditable(false);
	        col.add(username.document);
	        col.add(roles.document);
	        col.add(password.document);
	        col.add(email.document);
	        return col;
	    }else if(type == "role") {
	        Column name = new Column("name", Column.DataType.Text, true, true);
	        name.setIsDeletable(false);
	        name.setIsEditable(false);
	        col.add(name.document);
	        return col;
	   }
	    return col;
	}
	
	/**
	 * 
	 * _tableValidation
	 * 
	 * @param tableName
	 * @return
	 */
	static boolean _tableValidation(String tableName){
		char c = tableName.charAt(0);
		boolean isDigit = (c >= '0' && c <= '9');
		if(isDigit){
			return false;
		}
		
		if(tableName.matches("^S+$")){
			return false;
		}
		
		
	Pattern pattern = Pattern.compile("[~`!#$%\\^&*+=\\-\\[\\]\\';,/{}|\":<>\\?]");
		if(pattern.matcher(tableName).find()){
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * _columnNameValidation
	 * 
	 * 
	 * @param columnName
	 * @return
	 */
	static boolean _columnNameValidation(String columnName){
		char c = columnName.charAt(0);
		boolean isDigit = (c >= '0' && c <= '9');
		if(isDigit){
			return false;
		}
		
		if(columnName.matches("^S+$")){
			return false;
		}
		
		Pattern pattern = Pattern.compile("[~`!#$%\\^&*+=\\-\\[\\]\\';,/{}|\":<>\\?]");
		if(pattern.matcher(columnName).find()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * _columnValidation
	 * 
	 * 
	 * @param column
	 * @param table
	 * @return
	 */
	static boolean _columnValidation(Column column, CloudTable table){
		ArrayList<String> defaultColumns = new ArrayList<String>();
		defaultColumns.add("id");
		defaultColumns.add("createdAt");
		defaultColumns.add("updatedAt");
		defaultColumns.add("ACL");
		if(table.getTableType().toLowerCase() == "user"){
			defaultColumns.add("username");
			defaultColumns.add("email");
			defaultColumns.add("password");
			defaultColumns.add("roles");
		}else if(table.getTableType().toLowerCase() == "role"){
			defaultColumns.add("name");
		}
		
		String colName = column.getColumnName().toLowerCase();
		int index = defaultColumns.indexOf(colName);
		
		if(index > -1){
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * _isModified
	 * 
	 * 
	 * @param object
	 * @param columnName
	 */
	static void _isModified(CloudObject object, String columnName){
	
		ArrayList<String> modifiedColumns = new ArrayList<String>();
		JSONArray col = new JSONArray(object.document.get("_modifiedColumns").toString());
		for(int i=0;i < col.length();i++){
			modifiedColumns.add( col.getString(i));
		}
		object.document.put("_isModified", true);
		
		if(modifiedColumns.contains(columnName)){
			modifiedColumns.clear();
			modifiedColumns.add(columnName);
		}else{
			modifiedColumns.add(columnName);
		}
		object.document.put("_modifiedColumns", modifiedColumns);
	}
	
	/**
	 * 
	 * _validateNotificationQuery
	 * 
	 * 
	 * @param object
	 * @param query
	 */
	static void _validateNotificationQuery(CloudObject object, CloudQuery query){
		
	}
	
	static String  _makeString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	static  String _getSessionId(){
		String session = SqlLite.getSessionId();
		return session;
	}
	
	static  void  _setSessionId(String session){
		SqlLite.setSessionId(session);
	}
	
	static  void  _deleteSessionId(){
		SqlLite.deleteSessionId();
	}
	
	static ArrayList<String> _toStringArray(JSONArray obj1){
		ArrayList<String> obj = new ArrayList<String>();
		for(int i=0; i<obj1.length(); i++){
			obj.add(obj1.getString(i));
		}
		return obj;
	}
	
	static ArrayList<Object> _toObjectArray(JSONArray obj1){
		ArrayList<Object> obj = new ArrayList<Object>();
		for(int i=0; i<obj1.length(); i++){
			obj.add(obj1.get(i));
		}
		return obj;
	}
}