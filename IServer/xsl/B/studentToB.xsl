<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="students">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<STUDENTS>
			<xsl:for-each select="student">
			<STUDENT>
				<ID>
					<xsl:value-of select="id"></xsl:value-of>
				</ID>
				<NAME>
					<xsl:value-of select="name"></xsl:value-of>	
				</NAME>
				<GENDER>
					<xsl:value-of select="sex"/>
				</GENDER>
				<MAJOR>
					<xsl:value-of select="major"/>
				</MAJOR>
			</STUDENT>
			</xsl:for-each>
		</STUDENTS>
	</xsl:template>
</xsl:stylesheet>