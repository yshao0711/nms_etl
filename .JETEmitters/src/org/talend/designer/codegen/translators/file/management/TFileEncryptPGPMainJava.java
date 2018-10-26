package org.talend.designer.codegen.translators.file.management;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class TFileEncryptPGPMainJava
{
  protected static String nl;
  public static synchronized TFileEncryptPGPMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileEncryptPGPMainJava result = new TFileEncryptPGPMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t";
  protected final String TEXT_2 = "     " + NL + "org.talend.pgp.PGPEncrypt.encryptFile(";
  protected final String TEXT_3 = ",";
  protected final String TEXT_4 = ",";
  protected final String TEXT_5 = ",";
  protected final String TEXT_6 = ");" + NL + " " + NL + " " + NL + "              " + NL + " " + NL + "        " + NL + "\t\t" + NL + "        ";
  protected final String TEXT_7 = NL + "        ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;

	INode node = (INode)codeGenArgument.getArgument();

	String cid = node.getUniqueName();

	String source = ElementParameterParser.getValue(node, "__DECRYPTED_FILE__");	
	String destination = ElementParameterParser.getValue(node, "__ENCRYPTED_FILE__");	
	String publicKey = ElementParameterParser.getValue(node, "__PUBLIC_KEY__");
	boolean remove = ElementParameterParser.getValue(node,"__REMOVE_SOURCE__").equals("true");
	

    
	
	

    stringBuffer.append(TEXT_2);
    stringBuffer.append(source);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(destination);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(publicKey);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(remove);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
