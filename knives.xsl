<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:main="http://main.com/" 
    xmlns:features="http://features.com/">
    
    <xsl:output method="html" indent="yes" />
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Collection of knives</title>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="main:knives">
        <table border="1">
            <tr>
                <th>id</th>
                <th>type</th>
                <th>handy</th>
                <th>origin</th>
                <th>length</th>
                <th>width</th>
                <th>material</th>
                <th>handle</th>
                <th>wood</th>
                <th>drainage</th>
                <th>value</th>
            </tr>
            <xsl:for-each select="main:knife"> 
                <tr>
                    <tr>
                        <td><xsl:value-of select="@id"/></td>  
                        <td><xsl:value-of select="main:type"/></td>
                        <td><xsl:value-of select="main:handy"/></td>
                        <td><xsl:value-of select="main:origin"/></td>
                        <td><xsl:value-of select="main:visual/features:length"/></td>
                        <td><xsl:value-of select="main:visual/features:width"/></td>
                        <td><xsl:value-of select="main:visual/features:material"/></td>
                        <td><xsl:value-of select="main:visual/features:handle"/></td>
                        <td><xsl:value-of select="main:visual/features:wood"/></td>
                        <td><xsl:value-of select="main:visual/features:drainage"/></td>
                        <td><xsl:value-of select="main:visual/features:value"/></td>
                    </tr>
                </tr>
            </xsl:for-each> 
        </table>
    </xsl:template>   
</xsl:stylesheet>