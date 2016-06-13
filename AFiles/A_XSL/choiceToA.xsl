<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="selections">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<selections>
			<xsl:for-each select="selection">
			<selection>
				<ѧ����>
					<xsl:value-of select="sid"></xsl:value-of>
				</ѧ����>
				<�γ̱��>
					<xsl:value-of select="cid" />
				</�γ̱��>
				<�ɼ�>
					<xsl:value-of select="score"/>
				</�ɼ�>
			</selection>
			</xsl:for-each>
		</selections>
	</xsl:template>
</xsl:stylesheet>