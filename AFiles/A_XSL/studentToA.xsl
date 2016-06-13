<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="students">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<students>
			<xsl:for-each select="student">
			<student>
				<ѧ��>
					<xsl:value-of select="id"></xsl:value-of>
				</ѧ��>
				<����>
					<xsl:value-of select="name"></xsl:value-of>	
				</����>
				<�Ա�>
					<xsl:value-of select="sex"/>
				</�Ա�>
				<Ժϵ>
					<xsl:value-of select="major"/>
				</Ժϵ>
			</student>
			</xsl:for-each>
		</students>
	</xsl:template>
</xsl:stylesheet>