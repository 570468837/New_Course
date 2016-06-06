<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="selections">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<courseSelections>
			<xsl:for-each select="selection">
			<courseSelection>
				<Sno>
					<xsl:value-of select="sid"></xsl:value-of>
				</Sno>
				<Cno>
					<xsl:value-of select="cid" />
				</Cno>
				<Grd>
					<xsl:value-of select="score"/>
				</Grd>
			</courseSelection>
			</xsl:for-each>
		</courseSelections>
	</xsl:template>
</xsl:stylesheet>