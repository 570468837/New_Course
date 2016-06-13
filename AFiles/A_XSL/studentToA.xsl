<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="students">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<student>
			<xsl:for-each select="student">
			<student>
				<学号>
					<xsl:value-of select="id"></xsl:value-of>
				</学号>
				<姓名>
					<xsl:value-of select="name"></xsl:value-of>	
				</姓名>
				<性别>
					<xsl:value-of select="sex"/>
				</性别>
				<院系>
					<xsl:value-of select="major"/>
				</院系>
			</student>
			</xsl:for-each>
		</student>
	</xsl:template>
</xsl:stylesheet>