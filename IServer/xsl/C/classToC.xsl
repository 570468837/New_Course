<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="courses">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<courses>
		<xsl:for-each select="course">
			<course>
				<Cno>
					<xsl:value-of select="id" />
				</Cno>
				<Cnm>
					<xsl:value-of select="name"></xsl:value-of>
				</Cnm>
				<Cpt>
					<xsl:value-of select="score" />
				</Cpt>
				<Tec>
					<xsl:value-of select="teacher" />
				</Tec>
				<Pla>
					<xsl:value-of select="location"/>	
				</Pla>
			</course>	
		</xsl:for-each>
		</courses>
	</xsl:template>
</xsl:stylesheet>