package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TFileInputTextFlatBeginJava
{
  protected static String nl;
  public static synchronized TFileInputTextFlatBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputTextFlatBeginJava result = new TFileInputTextFlatBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "    // ";
  protected final String TEXT_3 = " , ";
  protected final String TEXT_4 = NL + "\tde.jlo.talendcomp.flatfileimport.Importer ";
  protected final String TEXT_5 = " = new de.jlo.talendcomp.flatfileimport.Importer();" + NL + "\t";
  protected final String TEXT_6 = ".setDebug(";
  protected final String TEXT_7 = ");" + NL + "\t";
  protected final String TEXT_8 = ".setImportFile(";
  protected final String TEXT_9 = ");" + NL + "\tglobalMap.put(\"";
  protected final String TEXT_10 = "_FILENAME\", ";
  protected final String TEXT_11 = ".getImportFile()); " + NL + "\t";
  protected final String TEXT_12 = ".skipBOM(";
  protected final String TEXT_13 = ");" + NL + "\t";
  protected final String TEXT_14 = ".setSkipEmptyLines(";
  protected final String TEXT_15 = ");" + NL + "\t";
  protected final String TEXT_16 = ".setHasHeaderRow(";
  protected final String TEXT_17 = ");" + NL + "\t";
  protected final String TEXT_18 = ".setIgnoreNotNullConstraints(";
  protected final String TEXT_19 = ");";
  protected final String TEXT_20 = NL + "    ";
  protected final String TEXT_21 = ".setRowsToSkip(";
  protected final String TEXT_22 = ");";
  protected final String TEXT_23 = NL + "\t";
  protected final String TEXT_24 = ".setDelimiter(";
  protected final String TEXT_25 = ");";
  protected final String TEXT_26 = NL + "\t";
  protected final String TEXT_27 = ".setFileCharset(";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = NL + "\t";
  protected final String TEXT_30 = ".setIgnoreLinebreakInEnclosures(!";
  protected final String TEXT_31 = ");" + NL + "\t";
  protected final String TEXT_32 = ".setEnclosure(";
  protected final String TEXT_33 = ");" + NL + "\t";
  protected final String TEXT_34 = ".allowEnclosureInFieldContent(";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "\t// helper to configure fields and get values" + NL + "\tfinal class ImportHelper_";
  protected final String TEXT_37 = " {" + NL + "\t" + NL + "\t\tpublic void configureFields(de.jlo.talendcomp.flatfileimport.Importer importer) {" + NL + "\t\t";
  protected final String TEXT_38 = NL + "\t\t\timporter.addFieldDescription(de.jlo.talendcomp.flatfileimport.FieldDescription.createDelimited(" + NL + "\t\t\t\t\"";
  protected final String TEXT_39 = "\", // columnName" + NL + "\t\t\t\t\"";
  protected final String TEXT_40 = "\", // type" + NL + "\t\t\t\t";
  protected final String TEXT_41 = ", // nullable " + NL + "\t\t\t\t";
  protected final String TEXT_42 = ", // pos" + NL + "\t\t\t\t";
  protected final String TEXT_43 = ", // length" + NL + "\t\t\t\t";
  protected final String TEXT_44 = ", // pattern" + NL + "\t\t\t\t";
  protected final String TEXT_45 = ", // regex" + NL + "\t\t\t\t";
  protected final String TEXT_46 = ", // trim" + NL + "\t\t\t\t";
  protected final String TEXT_47 = ", // ignoreMissing" + NL + "\t\t\t\t";
  protected final String TEXT_48 = ", // defaultValue" + NL + "\t\t\t\t";
  protected final String TEXT_49 = ")); // alternative";
  protected final String TEXT_50 = NL + "\t\t\timporter.addFieldDescription(de.jlo.talendcomp.flatfileimport.FieldDescription.createRelativePos(" + NL + "\t\t\t\t\"";
  protected final String TEXT_51 = "\", // columnName" + NL + "\t\t\t\t\"";
  protected final String TEXT_52 = "\", // type" + NL + "\t\t\t\t";
  protected final String TEXT_53 = ", // nullable " + NL + "\t\t\t\t";
  protected final String TEXT_54 = ", // length" + NL + "\t\t\t\t";
  protected final String TEXT_55 = ", // pattern" + NL + "\t\t\t\t";
  protected final String TEXT_56 = ", // regex" + NL + "\t\t\t\t";
  protected final String TEXT_57 = ", // trim" + NL + "\t\t\t\t";
  protected final String TEXT_58 = ", // defaultValue" + NL + "\t\t\t\t";
  protected final String TEXT_59 = ")); // alternative";
  protected final String TEXT_60 = NL + "\t\t\timporter.addFieldDescription(de.jlo.talendcomp.flatfileimport.FieldDescription.createAbsolutePos(" + NL + "\t\t\t\t\"";
  protected final String TEXT_61 = "\", // columnName " + NL + "\t\t\t\t\"";
  protected final String TEXT_62 = "\", // type" + NL + "\t\t\t\t";
  protected final String TEXT_63 = ", // nullable " + NL + "\t\t\t\t";
  protected final String TEXT_64 = ", // pos" + NL + "\t\t\t\t";
  protected final String TEXT_65 = ", // length" + NL + "\t\t\t\t";
  protected final String TEXT_66 = ", // pattern" + NL + "\t\t\t\t";
  protected final String TEXT_67 = ", // regex" + NL + "\t\t\t\t";
  protected final String TEXT_68 = ", // trim" + NL + "\t\t\t\t";
  protected final String TEXT_69 = ", // defaultValue" + NL + "\t\t\t\t";
  protected final String TEXT_70 = ")); // alternative";
  protected final String TEXT_71 = NL + "\t\t\t// unknown position type: ";
  protected final String TEXT_72 = ", sorry this should never happen!";
  protected final String TEXT_73 = "    " + NL + "\t\t} // end of configureFields" + NL + "\t\t" + NL + "\t\tpublic void fillOutputFlow(de.jlo.talendcomp.flatfileimport.Importer importer, ";
  protected final String TEXT_74 = "Struct flow) throws Exception {";
  protected final String TEXT_75 = NL + "\t\t\t\tflow.";
  protected final String TEXT_76 = " = importer.getStringAt(";
  protected final String TEXT_77 = ", ";
  protected final String TEXT_78 = ");";
  protected final String TEXT_79 = NL + "\t\t\t\tflow.";
  protected final String TEXT_80 = " = importer.getIntegerAt(";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = ");";
  protected final String TEXT_83 = NL + "\t\t\t\tflow.";
  protected final String TEXT_84 = " = importer.getShortAt(";
  protected final String TEXT_85 = ", ";
  protected final String TEXT_86 = ");";
  protected final String TEXT_87 = NL + "\t\t\t\tflow.";
  protected final String TEXT_88 = " = importer.getLongAt(";
  protected final String TEXT_89 = ", ";
  protected final String TEXT_90 = ");";
  protected final String TEXT_91 = NL + "\t\t\t\tflow.";
  protected final String TEXT_92 = " = importer.getBigDecimalAt(";
  protected final String TEXT_93 = ", ";
  protected final String TEXT_94 = ");";
  protected final String TEXT_95 = NL + "\t\t\t\tflow.";
  protected final String TEXT_96 = " = importer.getDoubleAt(";
  protected final String TEXT_97 = ", ";
  protected final String TEXT_98 = ");";
  protected final String TEXT_99 = NL + "\t\t\t\tflow.";
  protected final String TEXT_100 = " = importer.getFloatAt(";
  protected final String TEXT_101 = ", ";
  protected final String TEXT_102 = ");";
  protected final String TEXT_103 = NL + "\t\t\t\tflow.";
  protected final String TEXT_104 = " = importer.getBooleanAt(";
  protected final String TEXT_105 = ", ";
  protected final String TEXT_106 = ");";
  protected final String TEXT_107 = NL + "\t\t\t\tflow.";
  protected final String TEXT_108 = " = importer.getDateAt(";
  protected final String TEXT_109 = ", ";
  protected final String TEXT_110 = ");";
  protected final String TEXT_111 = NL + "\t\t\t\tflow.";
  protected final String TEXT_112 = " = importer.getTimestampAt(";
  protected final String TEXT_113 = ", ";
  protected final String TEXT_114 = ");";
  protected final String TEXT_115 = NL + "\t\t\t\tflow.";
  protected final String TEXT_116 = " = importer.getCharAt(";
  protected final String TEXT_117 = ", ";
  protected final String TEXT_118 = ");";
  protected final String TEXT_119 = NL + "\t\t\t\tflow.";
  protected final String TEXT_120 = " = importer.getObjectAt(";
  protected final String TEXT_121 = ", ";
  protected final String TEXT_122 = ");";
  protected final String TEXT_123 = " \t";
  protected final String TEXT_124 = NL + "\t\t} // end of fillOutputFlow" + NL + "\t" + NL + "\t}" + NL + "\tfinal ImportHelper_";
  protected final String TEXT_125 = " helper_";
  protected final String TEXT_126 = " = new ImportHelper_";
  protected final String TEXT_127 = "();";
  protected final String TEXT_128 = NL + "\t";
  protected final String TEXT_129 = ".loadFieldDescriptionConfiguration(";
  protected final String TEXT_130 = ");";
  protected final String TEXT_131 = NL + "\ttry {" + NL + "\t\thelper_";
  protected final String TEXT_132 = ".configureFields(";
  protected final String TEXT_133 = ");" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_134 = "_HEADER_CONFIG_ERROR\", false);" + NL + "\t} catch (Exception e) {" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_135 = "_ERROR_MESSAGE\", e.getMessage());" + NL + "\t\tthrow e;" + NL + "\t}";
  protected final String TEXT_136 = NL + "    try {" + NL + "    \t";
  protected final String TEXT_137 = ".initialize();";
  protected final String TEXT_138 = NL + "        ";
  protected final String TEXT_139 = ".skipTopRows();";
  protected final String TEXT_140 = NL + "\t\t";
  protected final String TEXT_141 = ".skipHeaderRow();";
  protected final String TEXT_142 = NL + "\t\ttry {" + NL + "\t\t\t// reconfigure fields, will fail if no header line is set" + NL + "\t\t\t";
  protected final String TEXT_143 = ".setFindHeaderPosByRegex(";
  protected final String TEXT_144 = ");" + NL + "\t\t\t";
  protected final String TEXT_145 = ".reconfigureFieldDescriptionByHeaderLine();" + NL + "\t\t} catch (Exception e) {" + NL + "\t\t\tglobalMap.put(\"";
  protected final String TEXT_146 = "_HEADER_CONFIG_ERROR\", true);" + NL + "\t\t\tthrow e;\t\t" + NL + "\t\t}";
  protected final String TEXT_147 = NL + "\t} catch (Exception e) {" + NL + "\t\t";
  protected final String TEXT_148 = ".close(); // close file handle in case of errors" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_149 = "_ERROR_MESSAGE\", e.getMessage());" + NL + "\t\tthrow e;" + NL + "\t}" + NL + "\tint countLinesDelivered_";
  protected final String TEXT_150 = " = 0;" + NL + "\tint lineNumber_";
  protected final String TEXT_151 = " = -1;" + NL + "\tint countRejects_";
  protected final String TEXT_152 = " = 0;" + NL + "\ttry {" + NL + "\t\twhile (true) { // main loop will be closed in end section" + NL + "\t\t\t// retrieve next data set" + NL + "\t\t\tlineNumber_";
  protected final String TEXT_153 = "++;" + NL + "\t\t\t";
  protected final String TEXT_154 = " = new ";
  protected final String TEXT_155 = "Struct();";
  protected final String TEXT_156 = NL + "\t\t\t";
  protected final String TEXT_157 = " = null;";
  protected final String TEXT_158 = NL + "\t\t\ttry {" + NL + "\t\t\t\tif (";
  protected final String TEXT_159 = ".nextDataRow() == false) {" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}" + NL + "\t\t\t} catch (Exception e) {" + NL + "\t\t\t\tString message = \"nextDataRow failed in line \" + countLinesDelivered_";
  protected final String TEXT_160 = " + \":\" + e.getMessage();" + NL + "\t\t\t\tglobalMap.put(\"";
  protected final String TEXT_161 = "_ERROR_MESSAGE\", message);" + NL + "\t\t\t\t";
  protected final String TEXT_162 = " = null;";
  protected final String TEXT_163 = NL + "\t\t\t\t";
  protected final String TEXT_164 = ".close(); // close file handle in case of errors" + NL + "\t\t\t\tthrow new Exception(message, e);";
  protected final String TEXT_165 = NL + "\t\t\t\t";
  protected final String TEXT_166 = " = new ";
  protected final String TEXT_167 = "Struct();" + NL + "\t\t\t\t";
  protected final String TEXT_168 = ".lineNumber = lineNumber_";
  protected final String TEXT_169 = ";" + NL + "\t\t\t\t";
  protected final String TEXT_170 = ".line = ";
  protected final String TEXT_171 = ".getCurrentLine();" + NL + "\t\t\t\t";
  protected final String TEXT_172 = ".errorMessage = message;" + NL + "\t\t\t\tcountRejects_";
  protected final String TEXT_173 = "++;";
  protected final String TEXT_174 = NL + "\t\t\t}" + NL + "\t\t\tif (";
  protected final String TEXT_175 = " != null) {" + NL + "\t\t\t\t// retrieve values" + NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\thelper_";
  protected final String TEXT_176 = ".fillOutputFlow(";
  protected final String TEXT_177 = ", ";
  protected final String TEXT_178 = ");" + NL + "\t\t\t\t} catch (Exception e) {" + NL + "\t\t\t\t\tString message = \"fillOutputFlow failed in line \" + countLinesDelivered_";
  protected final String TEXT_179 = " + \":\" + e.getMessage();" + NL + "\t\t\t\t\tglobalMap.put(\"";
  protected final String TEXT_180 = "_ERROR_MESSAGE\", message);";
  protected final String TEXT_181 = NL + "   \t\t\t\t\t";
  protected final String TEXT_182 = ".close(); // close file handle in case of errors" + NL + "\t\t\t\t\tthrow new Exception(message, e);";
  protected final String TEXT_183 = NL + "\t\t\t\t\tcontinue;";
  protected final String TEXT_184 = NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\tcountLinesDelivered_";
  protected final String TEXT_185 = "++;" + NL + "\t\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();
    String file = ElementParameterParser.getValue(node, "__FILENAME__");
    String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
    boolean skipBOM = "true".equals(ElementParameterParser.getValue(node, "__IGNORE_BOM__"));
    String delimiter = ElementParameterParser.getValue(node, "__FIELDSEPARATOR__");
    String rowsToSkip = ElementParameterParser.getValue(node, "__LINES_TO_SKIP__");
	boolean useEnclosures = "true".equals(ElementParameterParser.getValue(node, "__USE_ENCLOSURES__"));
    String enclosure = ElementParameterParser.getValue(node, "__TEXT_ENCLOSURE__");
    if ("\"\"\"".equals(enclosure)) {
    	enclosure = "\"\\\"\"";
    }
	String allowEnclosureInText = ElementParameterParser.getValue(node, "__ALLOW_ENCLUSURE_IN_TEXT__");
    String splitRowFirst = ElementParameterParser.getValue(node, "__PARSE_ROW_FIRST__");
    String numberLocale = ElementParameterParser.getValue(node, "__NUMBER_COUNTRY__");
    String defaultDateFormat = ElementParameterParser.getValue(node, "__DEFAULT_DATE_FORMAT__");
    String skipEmptyLines = ElementParameterParser.getValue(node, "__REMOVE_EMPTY_ROW__");
    String ignoreNotNull = ElementParameterParser.getValue(node, "__IGNORE_NOT_NULL__");
    boolean trimAll = "true".equals(ElementParameterParser.getValue(node, "__TRIMALL__"));
    boolean hasColumnHeaderLine = "true".equals(ElementParameterParser.getValue(node, "__COLUMN_HEADER_LINE__"));
    boolean configByHeader = "true".equals(ElementParameterParser.getValue(node, "__CONFIG_DELIMITED_BY_HEADER__"));
    String useRegexToFindColumnInHeader = ElementParameterParser.getValue(node, "__CONFIG_HEADER_BY_REGEX__");
	@SuppressWarnings("unchecked")
	List<Map<String, String>> importDescList = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__FIELD_CONFIG__");
	Map<String, String> headerNameMap = new HashMap<String, String>();
	Map<String, String> posTypeMap = new HashMap<String, String>();
	Map<String, String> positionMap = new HashMap<String, String>();
	Map<String, String> lengthMap = new HashMap<String, String>();
	Map<String, String> regexMap = new HashMap<String, String>();
	Map<String, String> ignoreMissingMap = new HashMap<String, String>();
	Map<String, String> alternativeMap = new HashMap<String, String>();
    boolean allowAltNames = "true".equals(ElementParameterParser.getValue(node, "__ALLOW_ALT_COLUMNS__"));
	int columnIndex = 0;
	for (Map<String, String> fd : importDescList) {
		String schemaColumn = fd.get("SCHEMA_COLUMN");
		String columnHeader = fd.get("COLUMN_HEADER_NAME");
		if (columnHeader != null && columnHeader.isEmpty() == false) {
			headerNameMap.put(schemaColumn, columnHeader);
		}
		String type = fd.get("POSITIONING_TYPE");
		posTypeMap.put(schemaColumn, type);
		String pos = fd.get("POSITION");
		if (pos == null || pos.isEmpty()) {
		    if ("DEL".equals(type)) {
				pos = String.valueOf(columnIndex);
		    } else {
				pos = "-1";
		    }
		}
		positionMap.put(schemaColumn, pos);
		String length = fd.get("LENGTH");
		if (length == null || length.isEmpty()) {
			length = "0";
		}
		lengthMap.put(schemaColumn, length);
		String regex = fd.get("REGEX");
		regexMap.put(schemaColumn, regex);
		String ignoreMissing = fd.get("IGNORE_IF_MISSING");
		ignoreMissingMap.put(schemaColumn, ignoreMissing);
		if (allowAltNames) {
			String alternative = fd.get("ALTERNATIVE");
			alternativeMap.put(schemaColumn, alternative);
		}
		columnIndex++;
	}
	@SuppressWarnings("unchecked")
	List<Map<String, String>> trimList = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__TRIMSELECT__");
	Map<String, String> trimMap = new HashMap<String, String>();
	for (Map<String, String> entry : trimList) {
		String schemaColumn = entry.get("SCHEMA_COLUMN");
		String trim = trimAll ? "true" : entry.get("TRIM");
		trimMap.put(schemaColumn, trim);
	}
    String rejectConnName = "";
	List<? extends IConnection> rejectConns = node.getOutgoingConnections("REJECT");
	if (rejectConns != null && rejectConns.size() > 0) {
		IConnection rejectConn = rejectConns.get(0);
		rejectConnName = rejectConn.getName();
	}
    String mainConnName = null;
    String mainConnector = null;
	List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
    for (IConnection conn : conns) {
    	if (rejectConnName.equals(mainConnName) == false) {
        	mainConnName = conn.getName();
    		mainConnector = conn.getConnectorName();
    		break;
    	}
    }
	List<IMetadataColumn> listColumns = new ArrayList<IMetadataColumn>();
	IMetadataTable metadata = node.getMetadataFromConnector(mainConnector);
	if (metadata != null) {
   		listColumns = metadata.getListColumns();
	}
	boolean dieOnError = "true".equals(ElementParameterParser.getValue(node, "__DIE_ON_ERROR__"));
	String debug = ElementParameterParser.getValue(node, "__DEBUG__");
	boolean loadConfigFromFile = "true".equals(ElementParameterParser.getValue(node, "__LOAD_CONFIG_FROM_FILE__"));
	String configFile = ElementParameterParser.getValue(node, "__CONFIG_FILE__");
	boolean limitLength = "true".equals(ElementParameterParser.getValue(node, "__LIMIT_STRING_CONTENT_TO_LENGTH__"));

    stringBuffer.append(TEXT_2);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(debug);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(file);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(skipBOM);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(skipEmptyLines);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(hasColumnHeaderLine);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(ignoreNotNull);
    stringBuffer.append(TEXT_19);
      if (rowsToSkip != null && rowsToSkip.isEmpty() == false) { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(rowsToSkip);
    stringBuffer.append(TEXT_22);
      }
	if (loadConfigFromFile == false ) { 
        if (delimiter != null && delimiter.isEmpty() == false) { 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(delimiter);
    stringBuffer.append(TEXT_25);
        } 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_28);
        if (useEnclosures && enclosure != null && enclosure.isEmpty() == false) { 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(splitRowFirst);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(enclosure);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(allowEnclosureInText);
    stringBuffer.append(TEXT_35);
        } 
      } 
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append((limitLength ? "// limit length for delimited fields" : "") );
      if (loadConfigFromFile == false) {
      for (columnIndex = 0; columnIndex < listColumns.size(); columnIndex++) {
		IMetadataColumn column = listColumns.get(columnIndex);
		String columnName = column.getLabel();
		String header = headerNameMap.get(columnName);
		column.setComment(header);
		String type = column.getTalendType().substring(3);
		String pattern = null;
		String pos = positionMap.get(columnName);
		String posType = posTypeMap.get(columnName);
		String length = lengthMap.get(columnName);
		if ("0".equals(length)) {
			Integer cl = column.getLength();
			if (cl != null) {
				length = String.valueOf(cl);
			}
		}
		if ("Date".equals(type) || "Timestamp".equals(type)) {
			pattern = column.getPattern();
			if (pattern == null || pattern.trim().isEmpty()) {
				pattern = defaultDateFormat;
			}
		} else if (("String".equals(type) || "Boolean".equals(type)) == false) {
			pattern = numberLocale;
		}
		if (pattern == null || pattern.trim().isEmpty()) {
			pattern = "null";
		}
		String regex = regexMap.get(columnName);
		if (regex == null || regex.trim().isEmpty()) {
			regex = "null";
		}
		String trim = trimMap.get(columnName);
		String ignoreIfMissing = ignoreMissingMap.get(columnName);
		String defaultValue = column.getDefault();
		if (defaultValue == null || defaultValue.trim().isEmpty()) {
			defaultValue = "null";
		}
		String alternative = alternativeMap.get(columnName);
		if (alternative == null || alternative.trim().isEmpty()) {
			alternative = "null";
		}
		if ("DEL".equals(posType)) { 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(header != null ? header : columnName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(pos);
    stringBuffer.append(TEXT_42);
    stringBuffer.append((limitLength ? length : "0") );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(regex);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(ignoreIfMissing);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(alternative);
    stringBuffer.append(TEXT_49);
          } else if ("ABS".equals(posType) && "-1".equals(pos)) { 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(header != null ? header : columnName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(length);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(regex);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(alternative);
    stringBuffer.append(TEXT_59);
          } else if ("ABS".equals(posType) && "-1".equals(pos) == false) { 
    stringBuffer.append(TEXT_60);
    stringBuffer.append(header != null ? header : columnName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(pos);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(length);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(regex);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(alternative);
    stringBuffer.append(TEXT_70);
          } else { 
    stringBuffer.append(TEXT_71);
    stringBuffer.append(posType);
    stringBuffer.append(TEXT_72);
          }
      } // for
   } // if (loadFromConfigFile 
    stringBuffer.append(TEXT_73);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_74);
      for (columnIndex = 0; columnIndex < listColumns.size(); columnIndex++) {
		IMetadataColumn column = listColumns.get(columnIndex);
		String columnName = column.getLabel();
		String type = column.getTalendType().substring(3); 
		if ("String".equals(type)) { 
    stringBuffer.append(TEXT_75);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_78);
          } else if ("Integer".equals(type)) { 
    stringBuffer.append(TEXT_79);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_82);
          } else if ("Short".equals(type)) { 
    stringBuffer.append(TEXT_83);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_86);
          } else if ("Long".equals(type)) { 
    stringBuffer.append(TEXT_87);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_90);
          } else if ("BigDecimal".equals(type)) { 
    stringBuffer.append(TEXT_91);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_94);
          } else if ("Double".equals(type)) { 
    stringBuffer.append(TEXT_95);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_98);
          } else if ("Float".equals(type)) { 
    stringBuffer.append(TEXT_99);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_102);
          } else if ("Boolean".equals(type)) { 
    stringBuffer.append(TEXT_103);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_106);
          } else if ("Date".equals(type)) { 
    stringBuffer.append(TEXT_107);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_110);
          } else if ("Timestamp".equals(type)) { 
    stringBuffer.append(TEXT_111);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_114);
          } else if ("Character".equals(type)) { 
    stringBuffer.append(TEXT_115);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_118);
          } else { 
    stringBuffer.append(TEXT_119);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(columnIndex);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(column.isNullable());
    stringBuffer.append(TEXT_122);
          } 
    stringBuffer.append(TEXT_123);
      } 
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
      if (loadConfigFromFile) { 
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(configFile);
    stringBuffer.append(TEXT_130);
      } else { 
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
      } 
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
      if (rowsToSkip != null && rowsToSkip.isEmpty() == false) { 
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
      }
	if (hasColumnHeaderLine) { 
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    		if (configByHeader) { 
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(useRegexToFindColumnInHeader);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
          } 
      } 
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_155);
    	if (rejectConnName.isEmpty() == false) { 
    stringBuffer.append(TEXT_156);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_157);
      } 
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_162);
      if (dieOnError) { 
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_164);
      } else {
		if (rejectConnName.isEmpty() == false) { 
    stringBuffer.append(TEXT_165);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_173);
          } 
      } 
    stringBuffer.append(TEXT_174);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
      if (dieOnError) { 
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_182);
      } else { 
    stringBuffer.append(TEXT_183);
      } 
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_185);
    return stringBuffer.toString();
  }
}
