package org.talend.designer.codegen.translators.file.input;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;

public class TFileInputCSVFilterMainJava
{
  protected static String nl;
  public static synchronized TFileInputCSVFilterMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputCSVFilterMainJava result = new TFileInputCSVFilterMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    ";
  protected final String TEXT_2 = NL + "  ";
  protected final String TEXT_3 = " = null;";
  protected final String TEXT_4 = NL + "String value";
  protected final String TEXT_5 = " = null;" + NL + "" + NL + "if( nb_line_";
  protected final String TEXT_6 = " == 0 ) {";
  protected final String TEXT_7 = NL + "    if(";
  protected final String TEXT_8 = " == null){ " + NL + "\t  ";
  protected final String TEXT_9 = " = new ";
  protected final String TEXT_10 = "Struct();" + NL + "\t}";
  protected final String TEXT_11 = NL + "\t    ";
  protected final String TEXT_12 = ".";
  protected final String TEXT_13 = " = sb";
  protected final String TEXT_14 = ".toString();" + NL + "\t  ";
  protected final String TEXT_15 = NL + "    if(";
  protected final String TEXT_16 = " == null){ " + NL + "\t  ";
  protected final String TEXT_17 = " = new ";
  protected final String TEXT_18 = "Struct();" + NL + "\t}";
  protected final String TEXT_19 = NL + "        ";
  protected final String TEXT_20 = ".";
  protected final String TEXT_21 = " = sb";
  protected final String TEXT_22 = ".toString();";
  protected final String TEXT_23 = " " + NL + "} else {" + NL + "\tisRecordAvailable";
  protected final String TEXT_24 = " = csvReader";
  protected final String TEXT_25 = ".readRecord();" + NL + "\tif( ! isRecordAvailable";
  protected final String TEXT_26 = " ) {" + NL + "\t  break;" + NL + "\t}" + NL + "\tvalue";
  protected final String TEXT_27 = " = csvReader";
  protected final String TEXT_28 = ".get(lookupColumn";
  protected final String TEXT_29 = ");" + NL + "\t" + NL + "\t" + NL + "\tif(value";
  protected final String TEXT_30 = ".trim().equals(lookupValue";
  protected final String TEXT_31 = ".trim())) {" + NL + "\t  ";
  protected final String TEXT_32 = NL + "\t    if(";
  protected final String TEXT_33 = " == null){ " + NL + "\t\t  ";
  protected final String TEXT_34 = " = new ";
  protected final String TEXT_35 = "Struct();" + NL + "\t\t}";
  protected final String TEXT_36 = NL + "\t\t    if( nb_line_ok_";
  protected final String TEXT_37 = " == 0 && nb_line_";
  protected final String TEXT_38 = " == 0 ) {" + NL + "\t          ";
  protected final String TEXT_39 = ".";
  protected final String TEXT_40 = " = sb";
  protected final String TEXT_41 = ".toString() + \"\\n\" + csvReader";
  protected final String TEXT_42 = ".getRawRecord();" + NL + "\t        } else {" + NL + "\t\t      ";
  protected final String TEXT_43 = ".";
  protected final String TEXT_44 = " = csvReader";
  protected final String TEXT_45 = ".getRawRecord();" + NL + "\t        }" + NL + "\t\t  ";
  protected final String TEXT_46 = NL + "\t  nb_line_ok_";
  protected final String TEXT_47 = "++;" + NL + "\t} else {" + NL + "\t  ";
  protected final String TEXT_48 = NL + "\t    if(";
  protected final String TEXT_49 = " == null){ " + NL + "\t\t  ";
  protected final String TEXT_50 = " = new ";
  protected final String TEXT_51 = "Struct();" + NL + "\t\t}";
  protected final String TEXT_52 = NL + "\t        if(nb_line_reject_";
  protected final String TEXT_53 = " == 0 && nb_line_";
  protected final String TEXT_54 = " == 0 ) {" + NL + "\t          ";
  protected final String TEXT_55 = ".";
  protected final String TEXT_56 = " = sb";
  protected final String TEXT_57 = ".toString() + \"\\n\" + csvReader";
  protected final String TEXT_58 = ".getRawRecord();" + NL + "\t        } else {" + NL + "\t          ";
  protected final String TEXT_59 = ".";
  protected final String TEXT_60 = " = csvReader";
  protected final String TEXT_61 = ".getRawRecord();" + NL + "\t        }" + NL + "\t      ";
  protected final String TEXT_62 = " " + NL + "\t  nb_line_reject_";
  protected final String TEXT_63 = "++;" + NL + "\t}" + NL + "}" + NL + "" + NL + "nb_line_";
  protected final String TEXT_64 = "++;";
  protected final String TEXT_65 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<? extends IConnection> conns = node.getOutgoingSortedConnections();
List<? extends IConnection> connsFilter = node.getOutgoingConnections("FILTER");
List<? extends IConnection> connsReject = node.getOutgoingConnections("REJECT");

for ( IConnection conn : conns  ) { 
    stringBuffer.append(TEXT_2);
    stringBuffer.append( conn.getName() );
    stringBuffer.append(TEXT_3);
     }

List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas != null) && (metadatas.size() > 0)) {
  IMetadataTable metadata = metadatas.get(0);
  if (metadata != null) {
    columns = metadata.getListColumns();	
  }
}


    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    for (IConnection connFilter : connsFilter) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_10);
    
	if (connFilter.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {	    
	  for(IMetadataColumn column : columns) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    }
	}
  }
    for (IConnection connReject : connsReject) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_18);
    
    if (connReject.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
      for(IMetadataColumn columnReject : columns) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(columnReject.getLabel());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_22);
    }
    }
  }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_31);
    for (IConnection connFilter : connsFilter) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_35);
    
		if (connFilter.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {	    
		  for(IMetadataColumn column : columns) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(connFilter.getName() );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_45);
    }
		}
	  }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    for (IConnection connReject : connsReject) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_51);
    
	    if (connReject.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
	      for(IMetadataColumn columnReject : columns) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_55);
    stringBuffer.append(columnReject.getLabel());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(connReject.getName() );
    stringBuffer.append(TEXT_59);
    stringBuffer.append(columnReject.getLabel());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_61);
    }
	    }
	  }
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(TEXT_65);
    return stringBuffer.toString();
  }
}
