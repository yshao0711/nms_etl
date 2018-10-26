package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;

public class TFileInputCSVFilterEndJava
{
  protected static String nl;
  public static synchronized TFileInputCSVFilterEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputCSVFilterEndJava result = new TFileInputCSVFilterEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    globalMap.put(\"";
  protected final String TEXT_2 = "_NB_LINE\", nb_line_";
  protected final String TEXT_3 = ");" + NL + "    globalMap.put(\"";
  protected final String TEXT_4 = "_NB_LINE_OK\", nb_line_ok_";
  protected final String TEXT_5 = ");" + NL + "    globalMap.put(\"";
  protected final String TEXT_6 = "_NB_LINE_REJECT\", nb_line_reject_";
  protected final String TEXT_7 = ");";
  protected final String TEXT_8 = NL + NL + "} //End of main loop" + NL + "csvReader";
  protected final String TEXT_9 = ".close();" + NL + "out";
  protected final String TEXT_10 = ".close();" + NL + "reader";
  protected final String TEXT_11 = ".close();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();	
List<IMetadataTable> metadatas = node.getMetadataList();

if (metadatas != null && metadatas.size() > 0) {
  IMetadataTable metadata = metadatas.get(0);
  
  if (metadata != null) {
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    }
}
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
