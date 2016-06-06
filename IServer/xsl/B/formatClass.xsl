<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" />
	<xsl:template match="COURSES">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates />
		<courses>
			<xsl:for-each select="COURSE">
				<course>
					<id>
						<xsl:value-of select="ID"></xsl:value-of>
					</id>
					<name>
						<xsl:value-of select="NAME" />
					</name>
					<score>
						<xsl:value-of select="CREDIT"/>
					</score>
					<teacher>
						<xsl:value-of select="TEACHER" />
					</teacher>
					<location>
						<xsl:value-of select="CLASSROOM"/>
					</location>
				</course>
			</xsl:for-each>
		</courses>
	</xsl:template>
</xsl:stylesheet>