<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="students">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<students>
			<xsl:for-each select="student">
			<student>
				<Sno>
					<xsl:value-of select="id"></xsl:value-of>
				</Sno>
				<Snm>
					<xsl:value-of select="name"></xsl:value-of>	
				</Snm>
				<Sex>
					<xsl:value-of select="sex"/>
				</Sex>
				<Sde>
					<xsl:value-of select="major"/>
				</Sde>
			</student>
			</xsl:for-each>
		</students>
	</xsl:template>
</xsl:stylesheet>