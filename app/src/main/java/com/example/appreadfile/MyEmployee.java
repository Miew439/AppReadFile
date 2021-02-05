package com.example.appreadfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MyEmployee extends Activity implements View.OnClickListener{
    TextView listemp;
    EditText searchT;
    Button btnSearch, btnBack;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);
        listemp = findViewById(R.id.txtEmployee);
        searchT = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.ebtnSearch);
        btnBack = findViewById(R.id.ebtnBack);
        ShowAll();
        btnBack.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.ebtnBack:
                Intent intent = new Intent(getApplicationContext(), MyMenu.class);
                startActivity(intent);
                MyEmployee.this.finish();
                break;
            case R.id.ebtnSearch:
                String s = searchT.getText().toString();
                Search(s);
        }
    }

    public void Search(String s){
        listemp.setText("");
        try{
            InputStream is = getAssets().open("employeedata.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = (Element) doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for(int i = 0;i<nList.getLength();i++){
                Node node = nList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element2 = (Element) node;
                    String name = getValue("name",element2).toString();
                    if (name.indexOf(s)>=0){
                        listemp.setText(listemp.getText()+ "\nName : " + getValue("name",element2)+"\n");
                        listemp.setText(listemp.getText()+ "Surname : " + getValue("surname",element2)+"\n");
                        listemp.setText(listemp.getText()+ "-----------------------------------------------------------");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static  String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public void ShowAll() {
        try {
            InputStream is = getAssets().open("employeedata.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    String name = getValue("name", element2).toString();
                    listemp.setText(listemp.getText()+ "\nName : " + getValue("name",element2)+"\n");
                    listemp.setText(listemp.getText()+ "Surname : " + getValue("surname",element2)+"\n");
                    listemp.setText(listemp.getText()+ "-----------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
