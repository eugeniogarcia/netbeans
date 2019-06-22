<?xml version="1.0" encoding="UTF-8"?>
<jsp:root version="2.1" xmlns:df="http://java.sun.com/jsf/dynamicfaces" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html"
    xmlns:jsfExt="http://java.sun.com/jsf/extensions/dynafaces"  xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">


   
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page binding="#{Page1.page1}" id="page1">
            <webuijsf:html binding="#{Page1.html1}" id="html1">
                <webuijsf:head binding="#{Page1.head1}" id="head1">
                    <webuijsf:link binding="#{Page1.link1}" id="link1" url="/resources/stylesheet.css"/>
                    <webuijsf:script binding="#{Page1.script1}" id="script1" url="/resources/currencytrader.js"/>
                    <df:ajaxTransaction binding="#{Page1.pollTx}" execute="none" id="pollTx"
                        inputs="page1:html1:body1:form1:hiddenPollIndicator,page1:html1:body1:form1:hiddenRenderId" postReplace="customPostReplace"
                        render="page1:html1:body1:form1:openPositionZone:openPositionTable,page1:html1:body1:form1:exchangeRateTable" replaceElement="customReplace"/>
                </webuijsf:head>
                <webuijsf:body binding="#{Page1.body1}" id="body1" onLoad="startPolling();" onUnload="stopPolling();" style="-rave-layout: grid">
                    <webuijsf:form binding="#{Page1.form1}" id="form1" virtualFormsConfig="">
                        <webuijsf:table augmentTitle="false" binding="#{Page1.exchangeRateTable}" id="exchangeRateTable"
                            style="left: 24px; top: 240px; position: absolute; width: 420px" title="Exchange Rates" width="420">
                            <webuijsf:tableRowGroup binding="#{Page1.exchangeRateTableRowGroup}" id="exchangeRateTableRowGroup" rows="10"
                                sourceData="#{Page1.currencyPairProvider}" sourceVar="currentRow">
                                <webuijsf:tableColumn binding="#{Page1.exchangeRateCurrencyColumn}" headerText="Currency" id="exchangeRateCurrencyColumn">
                                    <webuijsf:staticText binding="#{Page1.exchangeRateCurrencyText}" id="exchangeRateCurrencyText" text="#{currentRow.value['name']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{Page1.exchangeRateSellColumn}" headerText="Sell" id="exchangeRateSellColumn" width="140">
                                    <webuijsf:image binding="#{Page1.exchangeRateSellImage}" height="12" id="exchangeRateSellImage"
                                        style="padding-left: 5px; float:right"
                                        url="#{ApplicationBean1.visibleChangeImageSrcMap[currentRow.value['sellPriceVisibleChange']]}" width="11"/>
                                    <webuijsf:staticText binding="#{Page1.exchangeRateSellText}" escape="false" id="exchangeRateSellText" text="#{currentRow.value['sellPriceText']}"/>
                                </webuijsf:tableColumn>
                                <webuijsf:tableColumn binding="#{Page1.exchangeRateBuyColumn}" headerText="Buy" id="exchangeRateBuyColumn" width="139">
                                    <webuijsf:image binding="#{Page1.exchangeRateBuyImage}" height="12" id="exchangeRateBuyImage"
                                        style="padding-left: 5px; float:right"
                                        url="#{ApplicationBean1.visibleChangeImageSrcMap[currentRow.value['buyPriceVisibleChange']]}" width="11"/>
                                    <webuijsf:staticText binding="#{Page1.exchangeRateBuyText}" escape="false" id="exchangeRateBuyText" text="#{currentRow.value['buyPriceText']}"/>
                                </webuijsf:tableColumn>
                            </webuijsf:tableRowGroup>
                        </webuijsf:table>
                        <jsfExt:ajaxZone binding="#{Page1.openAPositionPanelZone}" collectPostData="customCollectPostData" id="openAPositionPanelZone"
                            inspectElement="customInspectElementForPanelZone" render="form1:openPositionZone" style="border: 1px dotted gray; height: 94px; left: 24px; top: 480px; position: absolute; width: 430px; -rave-layout: grid">
                            <webuijsf:staticText binding="#{Page1.orderPairNameText}" id="orderPairNameText" style="left: 24px; top: 24px; position: absolute" text="Currency"/>
                            <webuijsf:dropDown binding="#{Page1.orderPairNameDropDown}" id="orderPairNameDropDown" items="#{ApplicationBean1.pairNameOptions}" style="left: 24px; top: 48px; position: absolute"/>
                            <webuijsf:staticText binding="#{Page1.orderTypeText}" id="orderTypeText" style="left: 144px; top: 24px; position: absolute" text="Sell/Buy"/>
                            <webuijsf:dropDown binding="#{Page1.orderTypeDropDown}" id="orderTypeDropDown" items="#{ApplicationBean1.typeOptions}" style="left: 144px; top: 48px; position: absolute"/>
                            <webuijsf:staticText binding="#{Page1.orderAmountText}" id="orderAmountText" style="left: 240px; top: 24px; position: absolute" text="Amount (thousands)"/>
                            <webuijsf:dropDown binding="#{Page1.orderAmountDropDown}" converter="#{Page1.integerConverter1}" id="orderAmountDropDown"
                                items="#{ApplicationBean1.amountOptions}" style="left: 240px; top: 48px; position: absolute"/>
                            <webuijsf:button actionExpression="#{Page1.openButton_action}" binding="#{Page1.openButton}" id="openButton"
                                style="margin: 10px; left: 350px; top: 38px; position: absolute" text="Open"/>
                        </jsfExt:ajaxZone>
                        <jsfExt:ajaxZone binding="#{Page1.openPositionZone}" collectPostData="customCollectPostData" id="openPositionZone"
                            render="form1:openPositionZone,form1:closedPositionZone,form1:totalProfitZone" style="height: 118px; left: 24px; top: 600px; position: absolute; -rave-layout: grid; width:710px">
                            <webuijsf:table augmentTitle="false" binding="#{Page1.openPositionTable}" id="openPositionTable"
                                style="left: 0px; top: 24px; position: absolute" title="Open Positions" width="710">
                                <webuijsf:tableRowGroup binding="#{Page1.openPositionTableRowGroup}" emptyDataMsg="There are no open positions."
                                    id="openPositionTableRowGroup" rows="10" sourceData="#{SessionBean1.openPositionProvider}" sourceVar="currentRow">
                                    <webuijsf:tableColumn binding="#{Page1.openPositionCurrencyColumn}" headerText="Currency" id="openPositionCurrencyColumn">
                                        <webuijsf:staticText binding="#{Page1.openPositionCurrencyText}" id="openPositionCurrencyText" text="#{currentRow.value['name']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.openPositionAmountColumn}" headerText="Amount (thousands)" id="openPositionAmountColumn">
                                        <webuijsf:staticText binding="#{Page1.openPositionAmountText}" id="openPositionAmountText" text="#{currentRow.value['amount']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.openPositionTypeColumn}" headerText="Sell/Buy" id="openPositionTypeColumn">
                                        <webuijsf:staticText binding="#{Page1.openPositionTypeText}" id="openPositionTypeText" text="#{currentRow.value['type']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.openPositionOpenPriceColumn}" headerText="Open Price" id="openPositionOpenPriceColumn">
                                        <webuijsf:staticText binding="#{Page1.openPositionOpenPriceText}" id="openPositionOpenPriceText" text="#{currentRow.value['openPriceText']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.currentPriceColumn}" headerText="Current Price" id="currentPriceColumn" width="110">
                                        <webuijsf:image binding="#{Page1.currentPriceImage}" height="12" id="currentPriceImage" style="padding-left: 5px; float:right"
                                            url="#{ApplicationBean1.visibleChangeImageSrcMap[currentRow.value['currentPriceVisibleChange']]}" width="11"/>
                                        <webuijsf:staticText binding="#{Page1.currentPriceText}" escape="false" id="currentPriceText" text="#{currentRow.value['closePriceText']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.floatingProfitColumn}" headerText="Floating Profit" id="floatingProfitColumn" width="160">
                                        <webuijsf:image binding="#{Page1.floatingProfitImage}" height="12" id="floatingProfitImage"
                                            style="padding-left: 5px; float:right"
                                            url="#{ApplicationBean1.visibleChangeImageSrcMap[currentRow.value['floatingProfitVisibleChange']]}" width="11"/>
                                        <webuijsf:staticText binding="#{Page1.floatingProfitText}" escape="false" id="floatingProfitText" text="#{currentRow.value['profitText']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.floatingCloseAmountColumn}" headerText="Close Amount" id="floatingCloseAmountColumn">
                                        <webuijsf:dropDown binding="#{Page1.floatingCloseAmountDropDown}" converter="#{Page1.integerConverter1}"
                                            id="floatingCloseAmountDropDown" items="#{currentRow.value['floatingCloseAmountOptions']}" selected="#{currentRow.value['floatingCloseAmount']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.closeColumn}" headerText="Close" id="closeColumn">
                                        <webuijsf:button actionExpression="#{Page1.closeButton_action}" binding="#{Page1.closeButton}" id="closeButton" text="Close"/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </jsfExt:ajaxZone>
                        <jsfExt:ajaxZone binding="#{Page1.closedPositionZone}" id="closedPositionZone" style="height: 118px; left: 754px; top: 600px; position: absolute; width: 348px; -rave-layout: grid">
                            <webuijsf:table augmentTitle="false" binding="#{Page1.closedPositionTable}" id="closedPositionTable"
                                style="left: 0px; top: 24px; position: absolute; width: 1px" title="Closed Positions" width="1">
                                <webuijsf:tableRowGroup binding="#{Page1.closedPositionTableRowGroup}" emptyDataMsg="There are no closed positions."
                                    id="closedPositionTableRowGroup" rows="10" sourceData="#{SessionBean1.closedPositionProvider}" sourceVar="currentRow">
                                    <webuijsf:tableColumn binding="#{Page1.closedPositionCurrencyColumn}" headerText="Currency" id="closedPositionCurrencyColumn">
                                        <webuijsf:staticText binding="#{Page1.closedPositionCurrencyText}" id="closedPositionCurrencyText" text="#{currentRow.value['name']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.closedPositionAmountColumn}" headerText="Amount (thousands)" id="closedPositionAmountColumn">
                                        <webuijsf:staticText binding="#{Page1.closedPositionAmountText}" id="closedPositionAmountText" text="#{currentRow.value['amount']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.closedPositionTypeColumn}" headerText="Sell/Buy" id="closedPositionTypeColumn">
                                        <webuijsf:staticText binding="#{Page1.closedPositionTypeText}" id="closedPositionTypeText" text="#{currentRow.value['type']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.closedPositionOpenPriceColumn}" headerText="Open Price" id="closedPositionOpenPriceColumn">
                                        <webuijsf:staticText binding="#{Page1.closedPositionOpenPriceText}" id="closedPositionOpenPriceText" text="#{currentRow.value['openPriceText']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.closedPositionClosePriceColumn}" headerText="Close Price" id="closedPositionClosePriceColumn">
                                        <webuijsf:staticText binding="#{Page1.closedPositionClosePriceText}" id="closedPositionClosePriceText" text="#{currentRow.value['closePriceText']}"/>
                                    </webuijsf:tableColumn>
                                    <webuijsf:tableColumn binding="#{Page1.profitColumn}" headerText="Profit" id="profitColumn">
                                        <webuijsf:staticText binding="#{Page1.profitText}" id="profitText" text="#{currentRow.value['profitText']}"/>
                                    </webuijsf:tableColumn>
                                </webuijsf:tableRowGroup>
                            </webuijsf:table>
                        </jsfExt:ajaxZone>
                        <jsfExt:ajaxZone binding="#{Page1.totalProfitZone}" id="totalProfitZone" style="height: 22px; left: 24px; top: 180px; position: absolute; width: 142px; -rave-layout: grid">
                            <webuijsf:staticText binding="#{Page1.totalProfitLabelText}" id="totalProfitLabelText"
                                style="color: rgb(43, 73, 107); font-size: 8pt; font-weight: normal; left: 3px; top: 2px; position: absolute;" text="Total Profit:"/>
                            <webuijsf:staticText binding="#{Page1.totalProfitDisplayText}" id="totalProfitDisplayText"
                                style="color: rgb(43, 73, 107); font-size: 18px; left: 96px; top: -1px; position: absolute" text="#{SessionBean1.totalProfitText}"/>
                        </jsfExt:ajaxZone>
                        <webuijsf:staticText binding="#{Page1.openAPositionPanelText}" id="openAPositionPanelText"
                            style="color: gray; left: 24px; top: 465px; position: absolute" text="Open a Position"/>
                        <webuijsf:hiddenField binding="#{Page1.hiddenPollIndicator}" id="hiddenPollIndicator" text="pollRequest"/>
                        <webuijsf:hiddenField binding="#{Page1.hiddenRenderId}" id="hiddenRenderId" text="#{Page1.initialRenderId}"/>
                        <h:panelGrid binding="#{Page1.headerPanel1}" id="headerPanel1" style="left: 0px; top: 0px; position: absolute">
                            <h:panelGrid binding="#{Page1.titlePanel1}" columns="1" id="titlePanel1"
                                style="background-image: url(resources/masthead_background.gif); height: 120px" width="744">
                                <webuijsf:staticText binding="#{Page1.appNameText}" id="appNameText"
                                    style="color: rgb(255, 255, 255); font-family: 'Georgia','Times New Roman','times',serif; font-size: 24px; font-weight: bold" text="Currency Trader"/>
                                <webuijsf:staticText binding="#{Page1.tagLineText}" id="tagLineText"
                                    style="color: rgb(255, 255, 255); font-family: Georgia,'Times New Roman',times,serif; font-size: 10px; font-weight: bold" text="Powered by NetBeans Visual Web and Dynamic Faces Technology"/>
                            </h:panelGrid>
                            <h:panelGrid binding="#{Page1.subPanel1}" columns="3" id="subPanel1" style="height: 24px; background-color: rgb(221, 226, 238);" width="744">
                                <h:panelGrid binding="#{Page1.spacingPanel1}" id="spacingPanel1" style="height: 15px" width="431"/>
                                <webuijsf:imageHyperlink binding="#{Page1.imageHyperlink2}" id="imageHyperlink2" imageURL="resources/home.gif"
                                    style="background-color: rgb(221, 226, 238); color: rgb(0, 0, 0); font-family: 'Georgia','Times New Roman','times',serif; font-size: 10px; font-weight: bold"
                                    text="Home" url="http://www.netbeans.org"/>
                                <webuijsf:imageHyperlink binding="#{Page1.imageHyperlink1}" id="imageHyperlink1" imageURL="resources/help.gif"
                                    style="background-color: rgb(221, 226, 238); color: rgb(0, 0, 0); font-family: 'Georgia','Times New Roman','times',serif; font-size: 10px; font-weight: bold"
                                    text="Help" url="/Readme.html"/>
                            </h:panelGrid>
                        </h:panelGrid>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
