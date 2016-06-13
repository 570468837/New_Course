<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="selections">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<selection>
			<xsl:for-each select="selection">
			<selection>
				<学生编号>
					<xsl:value-of select="sid"></xsl:value-of>
				</学生编号>
				<课程编号>
					<xsl:value-of select="cid" />
				</课程编号>
				<成绩>
					<xsl:value-of select="score"/>
				</成绩>
			</selection>
			</xsl:for-each>
		</selection>
	</xsl:template>
</xsl:stylesheet>