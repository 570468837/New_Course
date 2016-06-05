<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="STUDENTS">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<students>
			<xsl:for-each select="STUDENT">
				<student>
					<id>
						<xsl:value-of select="ID"></xsl:value-of>
					</id>
					<name>
						<xsl:value-of select="NAME"/>
					</name>
					<sex>
						<xsl:value-of select="GENDER"></xsl:value-of>
					</sex>
					<major>
						<xsl:value-of select="MAJOR"/>
					</major>
				</student>
			</xsl:for-each>
		</students>
	</xsl:template>
</xsl:stylesheet>