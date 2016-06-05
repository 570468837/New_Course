<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="courseSelections">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<selections>
			<xsl:for-each select="courseSelection">
				<selection>
					<sid>
						<xsl:value-of select="Sno"></xsl:value-of>
					</sid>
					<cid>
						<xsl:value-of select="Cno"></xsl:value-of>
					</cid>
					<score>
						<xsl:value-of select="Grd"/>
					</score>
				</selection>
			</xsl:for-each>
		</selections>
	</xsl:template>
</xsl:stylesheet>