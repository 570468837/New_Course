<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" />
	<xsl:template match="course">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates />
		<courses>
			<xsl:for-each select="course">
				<course>
					<id>
						<xsl:value-of select="课程编号"></xsl:value-of>
					</id>
					<name>
						<xsl:value-of select="课程名称" />
					</name>
					<score>
						<xsl:value-of select="学分"/>
					</score>
					<teacher>
						<xsl:value-of select="授课老师" />
					</teacher>
					<location>
						<xsl:value-of select="授课地点"/>
					</location>
				</course>
			</xsl:for-each>
		</courses>
	</xsl:template>
</xsl:stylesheet>