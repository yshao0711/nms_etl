package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TFileInputTextFlatEndJava
{
  protected static String nl;
  public static synchronized TFileInputTextFlatEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputTextFlatEndJava result = new TFileInputTextFlatEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t} // end of while of ";
  protected final String TEXT_3 = NL + "\t} finally { // finally from try of ";
  protected final String TEXT_4 = NL + "\t\t";
  protected final String TEXT_5 = ".close(); // close file handle" + NL + "\t}" + NL + "\tglobalMap.put(\"";
  protected final String TEXT_6 = "_NB_LINE\", countLinesDelivered_";
  protected final String TEXT_7 = "); " + NL + "\tglobalMap.put(\"";
  protected final String TEXT_8 = "_NB_REJECTED\", countRejects_";
  protected final String TEXT_9 = "); ";
  protected final String TEXT_10 = NL + "\ttry {" + NL + "\t\t";
  protected final String TEXT_11 = ".saveConfigToFile(";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = ");" + NL + "\t} catch (Exception e) {" + NL + "\t\tglobalMap.put(\"";
  protected final String TEXT_14 = "_ERROR_MESSAGE\", e.getMessage());" + NL + "\t\tthrow e;\t" + NL + "\t} ";
  protected final String TEXT_15 = "\t" + NL + "\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();
	boolean loadConfigFromFile = "true".equals(ElementParameterParser.getValue(node, "__LOAD_CONFIG_FROM_FILE__"));
	boolean saveConfigToFile = "true".equals(ElementParameterParser.getValue(node, "__SAVE_CONFIG_TO_FILE__"));
	String configFileToSave = ElementParameterParser.getValue(node, "__CONFIG_FILE_BACKUP__");
	boolean allowFieldsNotFound = "true".equals(ElementParameterParser.getValue(node, "__ALLOW_FIELDS_NOT_FOUND_IN_CURRENT_FILE__"));

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
      if (loadConfigFromFile == false && saveConfigToFile) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(configFileToSave);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(allowFieldsNotFound);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
      } 
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
