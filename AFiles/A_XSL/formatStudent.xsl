<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="STUDENTS">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<students>
			<xsl:for-each select="STUDENT">
				<student>
					<id>
						<xsl:value-of select="学号"></xsl:value-of>
					</id>
					<name>
						<xsl:value-of select="姓名"/>
					</name>
					<sex>
						<xsl:value-of select="性别"></xsl:value-of>
					</sex>
					<major>
						<xsl:value-of select="院系"/>
					</major>
				</student>
			</xsl:for-each>
		</students>
	</xsl:template>
</xsl:stylesheet>