<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="selections">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<SELECTIONS>
			<xsl:for-each select="selection">
			<SELECTION>
				<STUDENT_ID>
					<xsl:value-of select="sid"></xsl:value-of>
				</STUDENT_ID>
				<COURSE_ID>
					<xsl:value-of select="cid" />
				</COURSE_ID>
				<GRADE>
					<xsl:value-of select="score"/>
				</GRADE>
			</SELECTION>
			</xsl:for-each>
		</SELECTIONS>
	</xsl:template>
</xsl:stylesheet>