<?xml version="1.0"?>
<?mso-application progid="Excel.Sheet"?>
<Workbook xmlns="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:o="urn:schemas-microsoft-com:office:office"
 xmlns:x="urn:schemas-microsoft-com:office:excel"
 xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet"
 xmlns:html="http://www.w3.org/TR/REC-html40">
 <DocumentProperties xmlns="urn:schemas-microsoft-com:office:office">
  <Created>2006-09-16T00:00:00Z</Created>
  <LastSaved>2018-03-30T05:43:38Z</LastSaved>
  <Version>14.00</Version>
 </DocumentProperties>
 <OfficeDocumentSettings xmlns="urn:schemas-microsoft-com:office:office">
  <AllowPNG/>
  <RemovePersonalInformation/>
 </OfficeDocumentSettings>
 <ExcelWorkbook xmlns="urn:schemas-microsoft-com:office:excel">
  <WindowHeight>8010</WindowHeight>
  <WindowWidth>14805</WindowWidth>
  <WindowTopX>240</WindowTopX>
  <WindowTopY>105</WindowTopY>
  <ProtectStructure>False</ProtectStructure>
  <ProtectWindows>False</ProtectWindows>
 </ExcelWorkbook>
 <Styles>
  <Style ss:ID="Default" ss:Name="Normal">
   <Alignment ss:Vertical="Bottom"/>
   <Borders/>
   <Font ss:FontName="宋体" ss:Size="11" ss:Color="#000000"/>
   <Interior/>
   <NumberFormat/>
   <Protection/>
  </Style>
  <Style ss:ID="s16">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
  </Style>
  <Style ss:ID="s17">
   <Alignment ss:Horizontal="Center" ss:Vertical="Bottom"/>
  </Style>
  <Style ss:ID="s18">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <Font ss:FontName="宋体" x:CharSet="134" ss:Size="11" ss:Color="#000000"
    ss:Bold="1"/>
   <NumberFormat ss:Format="&quot;¥&quot;#,##0.0000;[Red]&quot;¥&quot;#,##0.0000"/>
  </Style>
  <Style ss:ID="s19">
   <Alignment ss:Horizontal="Right" ss:Vertical="Bottom"/>
   <NumberFormat ss:Format="&quot;¥&quot;#,##0.0000;[Red]&quot;¥&quot;#,##0.0000"/>
  </Style>
 </Styles>
 <Worksheet ss:Name="Sheet1">
  <Table ss:ExpandedColumnCount="5" ss:ExpandedRowCount="${expandedRowCount+1}" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
   <Column ss:StyleID="s17" ss:AutoFitWidth="0"/>
   <Column ss:StyleID="s17" ss:AutoFitWidth="0" ss:Width="84"/>
   <Column ss:StyleID="s17" ss:AutoFitWidth="0" ss:Width="87.75"/>
   <Column ss:StyleID="s19" ss:AutoFitWidth="0" ss:Width="93"/>
   <Column ss:StyleID="s19" ss:AutoFitWidth="0" ss:Width="82.5"/>
   <Row>
    <Cell ss:StyleID="s16"><Data ss:Type="String">序号</Data></Cell>
    <Cell ss:StyleID="s16"><Data ss:Type="String">P/N</Data></Cell>
    <Cell ss:StyleID="s16"><Data ss:Type="String">CUST_P/N</Data></Cell>
    <Cell ss:StyleID="s18"><Data ss:Type="String">采购未税价</Data></Cell>
    <Cell ss:StyleID="s18"><Data ss:Type="String">销售未税价</Data></Cell>
   </Row>
  <#list orderItems as order>
      <Row>
          <Cell><Data ss:Type="Number">${order_index+1}</Data></Cell>
          <Cell><Data ss:Type="String">${order.pn?if_exists}</Data></Cell>
          <Cell><Data ss:Type="String">${order.pn2?if_exists}</Data></Cell>
          <Cell><Data ss:Type="Number">${order.unit_price_net?if_exists}</Data></Cell>
          <Cell><Data ss:Type="Number">${order.outprice?if_exists}</Data></Cell>
      </Row>
  </#list>
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <Print>
    <ValidPrinterInfo/>
    <PaperSizeIndex>9</PaperSizeIndex>
    <HorizontalResolution>96</HorizontalResolution>
    <VerticalResolution>96</VerticalResolution>
   </Print>
   <Selected/>
   <Panes>
    <Pane>
     <Number>3</Number>
     <ActiveRow>7</ActiveRow>
     <ActiveCol>3</ActiveCol>
    </Pane>
   </Panes>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet2">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
 <Worksheet ss:Name="Sheet3">
  <Table ss:ExpandedColumnCount="1" ss:ExpandedRowCount="1" x:FullColumns="1"
   x:FullRows="1" ss:DefaultColumnWidth="54" ss:DefaultRowHeight="13.5">
  </Table>
  <WorksheetOptions xmlns="urn:schemas-microsoft-com:office:excel">
   <PageSetup>
    <Header x:Margin="0.3"/>
    <Footer x:Margin="0.3"/>
    <PageMargins x:Bottom="0.75" x:Left="0.7" x:Right="0.7" x:Top="0.75"/>
   </PageSetup>
   <ProtectObjects>False</ProtectObjects>
   <ProtectScenarios>False</ProtectScenarios>
  </WorksheetOptions>
 </Worksheet>
</Workbook>
