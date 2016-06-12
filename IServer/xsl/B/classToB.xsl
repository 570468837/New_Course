<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="courses">
		<!-- TODO: Auto-generated template -->
		<xsl:apply-templates></xsl:apply-templates>
		<COURSES>
		<xsl:for-each select="course">
			<COURSE>
				<ID>
					<xsl:value-of select="id" />
				</ID>
				<NAME>
					<xsl:value-of select="name"></xsl:value-of>
				</NAME>
				<CREDIT>
					<xsl:value-of select="score"></xsl:value-of>
				</CREDIT>
				<TEACHER>
					<xsl:value-of select="teacher" />
				</TEACHER>
				<CLASSROOM>
					<xsl:value-of select="location"/>	
				</CLASSROOM>
			</COURSE>	
		</xsl:for-each>
		</COURSES>
	</xsl:template>
</xsl:stylesheet>